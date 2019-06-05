package org.opengroup.archimate.physical

import org.opengroup.archimate.meta.element.physical.{MaterialElement, MaterialElementRelationships}
import org.opengroup.archimate.meta.layer.Physical

case class Material(
	name: String,
	desc: String = ""
) extends Physical
	with MaterialElement {

	case class MaterialRelationships(
		override private[archimate] implicit val tt: Material = Material.this
	) extends MaterialElementRelationships[Material]

	val rel: MaterialRelationships = MaterialRelationships()

}