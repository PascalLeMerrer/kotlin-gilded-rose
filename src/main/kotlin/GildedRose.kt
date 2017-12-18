package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {

            decreaseSellIn(i)

            when {
                itemIsBackstagePass(i) -> updateBackstagePassQuality(i)
                itemIsBrie(i) -> {
                    increaseItemQuality(i)
                    if (items[i].sellIn < 0) {
                        increaseItemQuality(i)
                    }
                }
                itemIsConjured(i) -> {
                    decreaseItemQuality(i)
                    decreaseItemQuality(i)
                }
                else -> {
                    decreaseItemQuality(i)
                    if (items[i].sellIn < 0) {
                        decreaseItemQuality(i)
                    }
                }
            }
        }
    }

    private fun updateBackstagePassQuality(i: Int) {
        if (items[i].sellIn < 0) {
            items[i].quality = 0
            return
        }

        increaseItemQuality(i)

        if (items[i].sellIn < 10) {
            increaseItemQuality(i)
        }

        if (items[i].sellIn < 5) {
            increaseItemQuality(i)
        }
    }

    private fun decreaseSellIn(i: Int) {
        if (!itemIsSulfuras(i)) {
            items[i].sellIn = items[i].sellIn - 1
        }
    }

    private fun itemIsBrie(i: Int) = items[i].name.equals("Aged Brie")
    private fun itemIsConjured(i: Int) = items[i].name.equals("Conjured Mana Cake")

    private fun itemIsSulfuras(i: Int) = items[i].name.equals("Sulfuras, Hand of Ragnaros")

    private fun itemIsBackstagePass(i: Int) = items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")

    private fun decreaseItemQuality(i: Int) {
        if (items[i].quality > 0 && !itemIsSulfuras(i)) {
            items[i].quality = items[i].quality - 1
        }
    }

    private fun increaseItemQuality(i: Int) {
        if (itemQualityIsNotMax(i)) {
            items[i].quality = items[i].quality + 1
        }
    }

    private fun itemQualityIsNotMax(i: Int) = items[i].quality < 50

}

