package com.gildedrose

import io.kotlintest.matchers.shouldBe
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
    }
}



