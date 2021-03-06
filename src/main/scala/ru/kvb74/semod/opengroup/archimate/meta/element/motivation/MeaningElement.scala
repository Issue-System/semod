package ru.kvb74.semod.opengroup.archimate.meta.element.motivation

import ru.kvb74.semod.meta.ElementName
import ru.kvb74.semod.opengroup.archimate.meta.element.StrategyCoreStructureBehaviorElement

trait MeaningElement
	extends MotivationElement

case object MeaningElement
	extends ElementName

trait MeaningElementProps[T <: MeaningElement]
	extends MotivationElementProps[T]

trait MeaningElementRelationships[T <: MeaningElement]
	extends MotivationElementRelationships[T] {

	// лишнее ограничение уже есть в ElementRelationships
	//	def associatedWith(dst: StakeholderElement): T = tt._rel.associatedWith(dst)

	// лишнее ограничение уже есть в ElementRelationships
	//	def associatedWith(dst: StrategyCoreStructureBehaviorElement): T = tt._rel.associatedWith(dst)

	_register(MeaningElement,
		//		JR.associatedWith(StakeholderElement),
		//		JR.associatedWith(StrategyCoreStructureBehaviorElement)
	)

}
