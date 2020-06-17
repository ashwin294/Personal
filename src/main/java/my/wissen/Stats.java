package my.wissen;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Stats {
	public static class StatisticsAggregatorImpl implements StatisticsAggregator {

		private Map<String, Stats.StatisticsAggregatorImpl.PriceCount> symbolMap = new ConcurrentHashMap<>();

		@Override
		public void putNewPrice(String symbol, double price) {
			// YOUR CODE HERE

			PriceCount priceCountObject = null;
			double currentAveragePrice = 0;
			int currentCount = 0;
			if (symbolMap.containsKey(symbol)) {
				priceCountObject = symbolMap.get(symbol);
				currentAveragePrice = priceCountObject.getAveragePrice();
				currentCount = priceCountObject.getSymbolCount();
			} else {
				priceCountObject = new PriceCount();
			}

			currentAveragePrice = ((currentAveragePrice * currentCount) + price) / (currentCount + 1);
			currentCount++;
			priceCountObject.setAveragePrice(currentAveragePrice);
			priceCountObject.setSymbolCount(currentCount);
			symbolMap.put(symbol, priceCountObject);
		}

		@Override
		public double getAveragePrice(String symbol) {
			// YOUR CODE HERE
			double averagePrice = 0;
			PriceCount priceCountObject = symbolMap.get(symbol);
			if (null != priceCountObject) {
				averagePrice = priceCountObject.getAveragePrice();
			}
			return averagePrice;
		}

		@Override
		public int getTickCount(String symbol) {
			// YOUR CODE HERE
			int tickCount = 0;
			PriceCount priceCountObject = symbolMap.get(symbol);
			if (null != priceCountObject) {
				tickCount = priceCountObject.getSymbolCount();
			}
			return tickCount;
		}

		private class PriceCount {

			private int symbolCount;

			private double averagePrice;

			public double getAveragePrice() {
				return averagePrice;
			}

			public void setAveragePrice(double averagePrice) {
				this.averagePrice = averagePrice;
			}

			public int getSymbolCount() {
				return symbolCount;
			}

			public void setSymbolCount(int symbolCount) {
				this.symbolCount = symbolCount;
			}
		}

	}

	////////////////// DO NOT MODIFY BELOW THIS LINE ///////////////////

	public interface StatisticsAggregator {
		// This is an input. Make note of this price.
		public void putNewPrice(String symbol, double price);

		// Get the average price
		public double getAveragePrice(String symbol);

		// Get the total number of prices recorded
		public int getTickCount(String symbol);
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			final StatisticsAggregator stats = new StatisticsAggregatorImpl();
			final Set<String> symbols = new TreeSet<>();

			String line = scanner.nextLine();
			String[] inputs = line.split(",");
			int threads = Integer.parseInt(inputs[0]);
			ExecutorService pool = Executors.newFixedThreadPool(threads);
			for (int i = 1; i < inputs.length; ++i) {
				String[] tokens = inputs[i].split(" ");
				final String symbol = tokens[0];
				symbols.add(symbol);
				final double price = Double.parseDouble(tokens[1]);
				pool.submit(new Runnable() {
					@Override
					public void run() {
						stats.putNewPrice(symbol, price);
					}
				});

			}
			pool.shutdown();
			try {
				pool.awaitTermination(5000, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (String symbol : symbols) {
				System.out.println(
						String.format("%s %.4f %d", symbol, stats.getAveragePrice(symbol), stats.getTickCount(symbol)));
			}
		}
		scanner.close();

	}
}