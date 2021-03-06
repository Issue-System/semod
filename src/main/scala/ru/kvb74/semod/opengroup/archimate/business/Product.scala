package ru.kvb74.semod.opengroup.archimate.business

import ru.kvb74.semod.opengroup.archimate.meta.element.business.{ProductElement, ProductElementProps, ProductElementRelationships}
import ru.kvb74.semod.opengroup.archimate.meta.layer.BusinessLayer

case class Product(
	name: String
) extends BusinessLayer
	with ProductElement {

	case class ProductRelationships(
		override private[semod] implicit val tt: Product = Product.this
	) extends ProductElementRelationships[Product]

	val rel: ProductRelationships = ProductRelationships()

	case class ProductProps(
		override private[semod] implicit val tt: Product = Product.this
	) extends ProductElementProps[Product]

	val prop: ProductProps = ProductProps()

	_registerPrefix("BPr")
}
