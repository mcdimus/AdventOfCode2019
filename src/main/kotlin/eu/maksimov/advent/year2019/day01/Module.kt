package eu.maksimov.advent.year2019.day01

data class Module(val mass: Int) {

    fun getFuelRequirement() = getRequiredFuel(mass)

    fun getCompleteFuelRequirement(): Int {
        val fuelRequirement = getFuelRequirement()
        return calculateCompleteFuelRequirement(currentFuel = fuelRequirement, total = fuelRequirement)
    }

    private tailrec fun calculateCompleteFuelRequirement(currentFuel: Int, total: Int): Int {
        if (currentFuel <= 0) {
            return total;
        }
        val req = getRequiredFuel(currentFuel)
        return calculateCompleteFuelRequirement(req, total + req)
    }

    private fun getRequiredFuel(mass: Int) = (Math.floorDiv(mass, 3) - 2).coerceAtLeast(0)

}
