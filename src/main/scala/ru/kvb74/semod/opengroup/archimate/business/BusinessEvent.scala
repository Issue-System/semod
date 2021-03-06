package ru.kvb74.semod.opengroup.archimate.business

import ru.kvb74.semod.opengroup.archimate.meta.element.business.{BusinessEventElement, BusinessEventElementProps, BusinessEventElementRelationships}
import ru.kvb74.semod.opengroup.archimate.meta.layer.BusinessLayer

case class BusinessEvent(
	name: String
) extends BusinessLayer
	with BusinessEventElement {

	case class BusinessEventRelationships(
		override private[semod] implicit val tt: BusinessEvent = BusinessEvent.this
	) extends BusinessEventElementRelationships[BusinessEvent]

	val rel: BusinessEventRelationships = BusinessEventRelationships()

	case class BusinessEventProps(
		override private[semod] implicit val tt: BusinessEvent = BusinessEvent.this
	) extends BusinessEventElementProps[BusinessEvent]

	val prop: BusinessEventProps = BusinessEventProps()

	_registerPrefix("BE")
}
