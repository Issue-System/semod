package ru.kvb74.semod.opengroup.archimate.meta.element.application

import ru.kvb74.semod.meta.ElementName
import ru.kvb74.semod.opengroup.archimate.meta.element._

trait ApplicationInteractionElement
	extends BehaviorElement
		with StrategyCoreStructureBehaviorElement
		with ApplicationInternalBehaviorElement

case object ApplicationInteractionElement
	extends ElementName

trait ApplicationInteractionElementProps[T <: ApplicationInteractionElement]
	extends StrategyCoreStructureBehaviorElementProps[T]
		with ApplicationInternalBehaviorElementProps[T]

/**
	* @see http://pubs.opengroup.org/architecture/archimate3-doc/chap09.html#_Toc489946064
	*/
trait ApplicationInteractionElementRelationships[T <: ApplicationInteractionElement]
	extends StrategyCoreStructureBehaviorElementRelationships[T]
		with ApplicationInternalBehaviorElementRelationships[T] {

	_register(ApplicationInteractionElement)

}
