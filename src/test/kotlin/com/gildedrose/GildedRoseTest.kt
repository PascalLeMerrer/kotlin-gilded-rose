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


        "new version should produce the same results than the legacy one " {
            val items = arrayOf(Item("+5 Dexterity Vest", 10, 20), //
                    Item("Aged Brie", 2, 0), //
                    Item("Elixir of the Mongoose", 5, 7), //
                    Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                    Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)
            )
            val app = GildedRose(items)

            var legacyItems = emptyArray<Item>()

            for(item in items) {
                legacyItems = legacyItems.plus(item.copy())
            }
            
            val legacyApp = LegacyGildedRose(legacyItems)

            for (day in 0..999) {
                app.updateQuality()
                legacyApp.updateQuality()

                for ((index, item) in items.withIndex()) {
                    item.name shouldBe legacyItems.get(index).name
                    item.quality shouldBe legacyItems.get(index).quality
                    item.sellIn shouldBe legacyItems.get(index).sellIn
                }
            }
        }
    }
}



