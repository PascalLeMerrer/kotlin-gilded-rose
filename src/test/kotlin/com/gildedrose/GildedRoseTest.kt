package com.gildedrose

import io.kotlintest.forAll
import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec

class GildedRoseTests : StringSpec() {
    init {
        "updating quality should not change name" {
            val items = arrayOf<Item>(Item("foo", 0, 0))
            val app = GildedRose(items)
            app.updateQuality()
            val item = app.items.get(0)
            item.name shouldBe "foo"
        }

        "updating a conjured item should decrease its quality twice as fast as other products" {
            val items = arrayOf<Item>(Item("Conjured Mana Cake", 10, 6))
            val app = GildedRose(items)
            app.updateQuality()
            val item = app.items.get(0)
            item.name shouldBe "Conjured Mana Cake"
            item.sellIn shouldBe 9
            item.quality shouldBe 4
            app.updateQuality()
            item.name shouldBe "Conjured Mana Cake"
            item.sellIn shouldBe 8
            item.quality shouldBe 2
        }


        "new version should produce the same results than the legacy one " {
            val items = arrayOf(Item("+5 Dexterity Vest", 10, 20), //
                    Item("Aged Brie", 2, 0), //
                    Item("Elixir of the Mongoose", 5, 7), //
                    Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                    Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)
            )
            val app = GildedRose(items)

            val legacyItems = items.map { it.copy() }.toTypedArray()
            val legacyApp = LegacyGildedRose(legacyItems)

            for (day in 0..999) {
                app.updateQuality()
                legacyApp.updateQuality()

                for ((item, legacyItem) in items.zip(legacyItems)) {
                    item.name shouldBe legacyItem.name
                    item.quality shouldBe legacyItem.quality
                    item.sellIn shouldBe legacyItem.sellIn
                }
            }
        }
    }
}



