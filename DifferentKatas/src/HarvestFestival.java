/*
You have cultivated a plant, and after months of hard work, the time has come to reap the flowers of your hard work. When it was growing, you added water and fertilizer, and kept a constant temperature. It's time check how much your plant has grown.

A plant is represented horizontally, from the base to the left, to the end to the right:

---@---@---@

The stem is represented by hyphens -, and the flowers are represented by symbols. A plant always starts with the stem, and always ends with flowers.

Four parameters will be given. The four parameters are:

    seed (string) - determines the type of flowers generated by the plant.
    water (integer) - each unit of water extends the portion of stem between the flowers. It also gives how many times the stems + flower clusters should be repeated
    fert (integer) - each unit of fertilizer increases the amount of flowers, grouped in clusters
    temp (integer) - if the temperature given is in the range of 20°C and 30°C, the plant grows normally, otherwise, all the flowers die except for one flower at the end of the stem.

Given the above parameters, your task is to return a string representing the plant.
 */
public class HarvestFestival {
    //    return 20 <= temp && temp <= 30 ? ("-".repeat(water) + Character.toString(seed).repeat(fert)).repeat(water) : "-".repeat(water * water) + seed;

    public static void main(String[] args) {
        System.out.println(plant(',', 3, 7, 25));
        System.out.println(plant('+', 1, 3, 15));

    }

    public static String plant(char seed, int water, int fert, int temp){
        String stem = "";
        String result = "";
        String flower = "";
        String s = String.valueOf(seed);
        for (int i = 0; i < water; i++) {
            stem += "-";
        }
        if ((temp <= 30) && (temp >=20)){
            while (fert > 0){
                flower += s;
                fert--;
            }
        }
        for (int i = 0; i < water; i++) {
            result += stem + flower;
        }
        if ((temp < 20) || (temp > 30)) {
            result += s;
        }
        return result;
    }
}
