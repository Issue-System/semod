package ru.kvb74.semod.opengroup.archimate.meta.element.implementation

import ru.kvb74.semod.Origin
import ru.kvb74.semod.meta.ElementName
import ru.kvb74.semod.opengroup.archimate.meta.element.{ArchimateElement, ArchimateElementRelationships}

trait GapElement
	extends ArchimateElement

case object GapElement
	extends ElementName

trait GapElementRelationships[T <: GapElement]
	extends ArchimateElementRelationships[T] {

	@Origin("http://pubs.opengroup.org/architecture/archimate3-doc/chap13.html#_Toc489946120")
	def associatedWith(from: PlateauElement, to: PlateauElement) = {
		tt._rel.associatedWith(from, "from")
		tt._rel.associatedWith(to, "to")
	}

	_register(GapElement)

}
