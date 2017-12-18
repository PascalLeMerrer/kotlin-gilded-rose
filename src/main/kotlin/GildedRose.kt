package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (item in items) {

            decreaseSellIn(item)

            when {
                isBackstagePass(item) -> updateBackstagePassQuality(item)
                isBrie(item) -> updateBrieQuality(item)
                isConjured(item) -> updateConjuredItemQuality(item)
                isSulfuras(item) -> {}
                else -> {
                    decreaseQuality(item)
                    if (item.sellIn < 0) {
                        decreaseQuality(item)
                    }
                }
            }
        }
    }

    private fun updateConjuredItemQuality(item: Item) {
        decreaseQuality(item)
        decreaseQuality(item)
    }

    private fun updateBrieQuality(item: Item) {
        increaseQuality(item)
        if (item.sellIn < 0) {
            increaseQuality(item)
        }
    }

    private fun updateBackstagePassQuality(item: Item) {
        if (item.sellIn < 0) {
            item.quality = 0
            return
        }

        increaseQuality(item)

        if (item.sellIn < 10) {
            increaseQuality(item)
        }

        if (item.sellIn < 5) {
            increaseQuality(item)
        }
    }

    private fun decreaseSellIn(item: Item) {
        if (!isSulfuras(item)) {
            item.sellIn = item.sellIn - 1
        }
    }

    private fun isBrie(item: Item) = item.name.equals("Aged Brie")
    private fun isConjured(item: Item) = item.name.equals("Conjured Mana Cake")

    private fun isSulfuras(item: Item) = item.name.equals("Sulfuras, Hand of Ragnaros")

    private fun isBackstagePass(item: Item) = item.name.equals("Backstage passes to a TAFKAL80ETC concert")

    private fun decreaseQuality(item: Item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1
        }
    }

    private fun increaseQuality(item: Item) {
        if (qualityIsNotMax(item)) {
            item.quality = item.quality + 1
        }
    }

    private fun qualityIsNotMax(item: Item) = item.quality < 50

}