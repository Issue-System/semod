package ru.kvb74.semod.plantuml

import java.io.ByteArrayOutputStream
import java.nio.charset.Charset

import net.sourceforge.plantuml.{FileFormat, FileFormatOption, SourceStringReader}
import ru.kvb74.semod.opengroup.archimate.meta.element.Element
import ru.kvb74.semod.opengroup.archimate.meta.layer.Layer
import ru.kvb74.semod.opengroup.archimate.meta.relationship.Relationship
import ru.kvb74.semod.opengroup.archimate.relationship.dependency.{Access, Influence}
import ru.kvb74.semod.opengroup.archimate.relationship.dynamic.Flow
import ru.kvb74.semod.opengroup.archimate.{Report, Resource}

import scala.compat.Platform.EOL
import scala.reflect.io.{File, Path}

object PlantUml {

	private def _normalize(text: String): String = text.replaceAll("\n", "\\\\n")

	private def renderElement(element: Element): String = {
		val sb = StringBuilder.newBuilder
		val kind = Resource.b.getString(element.fullElementName)
		element match {
			case layer: Layer =>
				sb.append(layer.layerName)
				sb.append("_")
				sb.append(element.elementName.replaceFirst(layer.layerName, ""))
			case _ =>
				sb.append(element.elementName)
		}
		sb.append(s"""(${element.id}, "${_normalize(element.name)}""")
		if (element.name.nonEmpty) {
			sb.append("\\n")
		}
		sb.append(s"""($kind)")""")
		sb.mkString
	}

	private def _renderInfluence(relationship: Influence): String = {
		val sb = StringBuilder.newBuilder
		val name = s"Rel_${relationship.asInstanceOf[Product].productPrefix}"
		val desc = Resource.b.getString(if (relationship.direct) name else s"${name}_back")
		val label = if (relationship.label.isEmpty) "" else s" (${relationship.label})"
		sb.append(name)
		sb.append(s"""(${relationship.src.id}, ${relationship.dst.id}, "$desc$label")""")
		sb.mkString
	}

	private def _renderGeneric(relationship: Relationship): String = {
		val sb = StringBuilder.newBuilder
		val name = s"Rel_${relationship.asInstanceOf[Product].productPrefix}"
		val desc = Resource.b.getString(if (relationship.direct) name else s"${name}_back")
		sb.append(name)
		sb.append(s"""(${relationship.src.id}, ${relationship.dst.id}, "$desc")""")
		sb.mkString
	}

	// TODO implement different Access modes
	private def _renderAccess(relationship: Access): String = _renderGeneric(relationship)

	// TODO implement Flow labels
	private def _renderFlow(relationship: Flow): String = _renderGeneric(relationship)

	private def renderRelationship(relationship: Relationship): String = relationship match {
		case rel: Influence => _renderInfluence(rel)
		case rel: Access => _renderAccess(rel)
		case rel: Flow => _renderFlow(rel)
		case _ => _renderGeneric(relationship)
	}

	/**
		* Настройки отчёта PlantUML
		*/
	trait Options {
		/**
			* Название отчёта
			*/
		val name: Option[String]
		/**
			* Заголовок отчёта
			*/
		val title: Option[String]
		/**
			* Верхний колонтитул
			*/
		val header: Option[String]
		/**
			* Нижний колонтитул
			*/
		val footer: Option[String]
		/**
			* Имя файла в который нужно выгрузить результат. Возможные расширения файла:
			* * .png — растровый файл в формате PNG
			* * .svg — векторный файл в формате SVG
			* * любой другой — текстовый файл в формате .puml
			*/
		val file: Option[String]
	}

	class OptionsBuilder {
		private var _name: Option[String] = None
		private var _title: Option[String] = None
		private var _header: Option[String] = None
		private var _footer: Option[String] = None
		private var _file: Option[String] = None

		def name(value: String): OptionsBuilder = {
			_name = Some(value)
			this
		}

		def name(value: Option[String]): OptionsBuilder = {
			_name = value
			this
		}

		def title(value: String): OptionsBuilder = {
			_title = Some(value)
			this
		}

		def title(value: Option[String]): OptionsBuilder = {
			_title = value
			this
		}

		def header(value: String): OptionsBuilder = {
			_header = Some(value)
			this
		}

		def header(value: Option[String]): OptionsBuilder = {
			_header = value
			this
		}

		def footer(value: String): OptionsBuilder = {
			_footer = Some(value)
			this
		}

		def footer(value: Option[String]): OptionsBuilder = {
			_footer = value
			this
		}

		def file(value: String): OptionsBuilder = {
			_file = Some(value)
			this
		}

		def file(value: Option[String]): OptionsBuilder = {
			_file = value
			this
		}

		def get: Options = OptionsInstance(_name, _title, _header, _footer, _file)
	}

	/**
		* Создаёт пустой построитель настроек
		*
		* @return
		*/
	def opt = new OptionsBuilder

	case class OptionsInstance(
		name: Option[String],
		title: Option[String],
		header: Option[String],
		footer: Option[String],
		file: Option[String]
	) extends Options

	private def renderToString(
		options: Options,
		elements: Set[Element],
		relationships: Set[Relationship]
	): String = {

		val sb = StringBuilder.newBuilder
		sb.append("@startuml")
		options.name.foreach(name => sb.append(s" $name"))
		sb.append(EOL)
		sb.append("!includeurl https://raw.githubusercontent.com/smeagol74/Archimate-PlantUML/master/Archimate.puml").append(EOL).append(EOL)

		options.header.foreach(header => sb.append(s"header ${_normalize(header)}").append(EOL).append(EOL))
		options.footer.foreach(footer => sb.append(s"footer ${_normalize(footer)}").append(EOL).append(EOL))
		options.title.foreach(title => sb.append(s"title ${_normalize(title)}").append(EOL).append(EOL))

		elements.map(renderElement).foreach(sb.append(_).append(EOL))
		relationships.map(renderRelationship).foreach(sb.append(_).append(EOL))

		sb.append("@enduml").append(EOL)
		sb.mkString
	}

	private object r {
		val png = ".*\\.png$".r
		val svg = ".*\\.svg$".r
	}

	/**
		* Если указана настройка `file`, то результат будет записан в файл, если же такая настройка не указана,
		* то текст в формате plant-uml будет возвращён в виде строки
		*/
	def render(
		options: Options,
		report: Report
	): String = {
		val source = renderToString(options, report.elements, report.relationships)
		options.file match {
			case Some(filePath) =>
				val file = File(Path(filePath))
				val ext = filePath.toLowerCase
				if (ext.matches(".*\\.png")) {
					val reader = new SourceStringReader(source)
					reader.outputImage(file.jfile)
				} else if (ext.matches(".*\\.svg")) {
					val reader = new SourceStringReader(source)
					val os = new ByteArrayOutputStream()
					reader.generateImage(os, new FileFormatOption(FileFormat.SVG))
					os.close()
					val svg = new String(os.toByteArray, Charset.forName("UTF-8"))
					file.writeAll(svg)
				} else {
					file.writeAll(source)
				}
				filePath
			case None =>
				source
		}
	}

}