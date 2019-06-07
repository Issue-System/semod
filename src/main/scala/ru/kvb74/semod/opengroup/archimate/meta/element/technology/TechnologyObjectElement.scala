package ru.kvb74.semod.opengroup.archimate.meta.element.technology

import ru.kvb74.semod.opengroup.archimate.meta.element._
import ru.kvb74.semod.opengroup.archimate.meta.element.business.BusinessObjectElement

trait TechnologyObjectElement
	extends PassiveStructureElement
		with StrategyCoreStructureBehaviorElement

case object TechnologyObjectElement
	extends ElementName

trait TechnologyObjectElementRelationships[T <: TechnologyObjectElement]
	extends StrategyCoreStructureBehaviorElementRelationships[T] {

	def realizes(dst: BusinessObjectElement): T = tt._rel.realizes(dst)

	_register(TechnologyObjectElement,
		JR.realizes(BusinessObjectElement)
	)

}