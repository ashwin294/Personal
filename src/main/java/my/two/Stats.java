package my.two;

import java.util.*;
import java.util.concurrent.*;

public class Stats {
	public static class StatisticsAggregatorImpl implements StatisticsAggregator {

		ConcurrentHashMap<String, ArrayList<Double>> stockPrices = new ConcurrentHashMap<>();

		@Override
		public void putNewPrice(String symbol, double price) {

			if (stockPrices.get(symbol) != null) {
				stockPrices.get(symbol).add(price);
			} else {
				ArrayList<Double> arrayList = new ArrayList<>();
				arrayList.add(price);
				stockPrices.put(symbol, arrayList);
			}

		}

		@Override
		public double getAveragePrice(String symbol) {
			if (stockPrices.get(symbol) != null) {
				double totalValue = 0.0;
				for (Double value : stockPrices.get(symbol)) {
					totalValue += value;
				}
				return totalValue / stockPrices.get(symbol).size();
			}
			return 0.0;
		}

		@Override
		public int getTickCount(String symbol) {
			if (stockPrices.get(symbol) != null) {
				return stockPrices.get(symbol).size();
			}
			return 0;
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