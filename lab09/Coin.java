import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
  Lab 9 - Exercise 5: an enum with a field and a method that returns the fewest coins.
*/
public enum Coin {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25);

    private final int cents;

    Coin(int cents) {
        this.cents = cents;
    }

    public int getCents() {
        return cents;
    }

    public static List<Coin> fewestCoins(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        List<Coin> coins = Arrays.stream(values())
                .sorted(Comparator.comparingInt(Coin::getCents).reversed())
                .toList();

        List<Coin> result = new ArrayList<>();
        int remaining = amount;
        for (Coin coin : coins) {
            while (remaining >= coin.cents) {
                result.add(coin);
                remaining -= coin.cents;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] amounts = {0, 3, 41, 68, 99};
        for (int amount : amounts) {
            List<Coin> coins = fewestCoins(amount);
            System.out.printf("%3d cents -> %d coin(s): %s%n", amount, coins.size(), coins);
        }
    }
}
