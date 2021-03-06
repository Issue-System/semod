package ru.kvb74.semod.opengroup.archimate.meta.element.technology

import ru.kvb74.semod.meta.ElementName
import ru.kvb74.semod.opengroup.archimate.meta.element.{BehaviorElement, StrategyCoreStructureBehaviorElement, StrategyCoreStructureBehaviorElementProps, StrategyCoreStructureBehaviorElementRelationships}

trait TechnologyInteractionElement
	extends BehaviorElement
		with StrategyCoreStructureBehaviorElement
		with TechnologyInternalBehaviorElement

case object TechnologyInteractionElement
	extends ElementName

trait TechnologyInteractionElementProps[T <: TechnologyInteractionElement]
	extends StrategyCoreStructureBehaviorElementProps[T]
		with TechnologyInternalBehaviorElementProps[T]

trait TechnologyInteractionElementRelationships[T <: TechnologyInteractionElement]
	extends StrategyCoreStructureBehaviorElementRelationships[T]
		with TechnologyInternalBehaviorElementRelationships[T] {

	_register(TechnologyInteractionElement)

}
