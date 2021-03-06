package ru.kvb74.semod.opengroup.archimate.meta.element.application

import ru.kvb74.semod.meta.{ElementName, RR}
import ru.kvb74.semod.opengroup.archimate.meta.element._
import ru.kvb74.semod.opengroup.archimate.meta.element.business.{BusinessInterfaceElement, BusinessInternalActiveStructureElement}
import ru.kvb74.semod.opengroup.archimate.meta.element.technology.NodeElement

trait ApplicationInterfaceElement
	extends ActiveStructureElement
		with StrategyCoreStructureBehaviorElement

case object ApplicationInterfaceElement
	extends ElementName

trait ApplicationInterfaceElementProps[T <: ApplicationInterfaceElement]
	extends StrategyCoreStructureBehaviorElementProps[T]

trait ApplicationInterfaceElementRelationships[T <: ApplicationInterfaceElement]
	extends StrategyCoreStructureBehaviorElementRelationships[T] {

	def assignedTo(dst: ApplicationServiceElement): T = tt._rel.assignedTo(dst)

	def serves(dst: ApplicationComponentElement): T = tt._rel.serves(dst)

	def serves(dst: BusinessInternalActiveStructureElement): T = tt._rel.serves(dst)

	def realizes(dst: BusinessInterfaceElement): T = tt._rel.realizes(dst)

	def serves(dst: NodeElement): T = tt._rel.serves(dst)

	_register(ApplicationInterfaceElement,
		RR.assignedTo(ApplicationServiceElement),
		RR.serves(ApplicationComponentElement),
		RR.serves(BusinessInternalActiveStructureElement),
		RR.realizes(BusinessInterfaceElement),
		RR.serves(NodeElement),
	)

}