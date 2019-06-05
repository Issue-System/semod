package org.opengroup.archimate.motivation

import org.opengroup.archimate.meta.element.motivation.{ValueElement, ValueElementRelationships}
import org.opengroup.archimate.meta.layer.Motivation

case class Value(
	name: String,
	desc: String = ""
) extends Motivation
	with ValueElement {

	case class ValueRelationships(
		override private[archimate] implicit val tt: Value = Value.this
	) extends ValueElementRelationships[Value]

	val rel: ValueRelationships = ValueRelationships()

}
