package com.skillenza.socgen.teams;

import java.util.Scanner;

public class Teams {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		short matches = scanner.nextShort();
		// scanner.nextLine();
		short teams = (short) (matches / 2);
		StringBuilder scores = new StringBuilder();
		short i = 0, j;
		byte[] teamA;
		short players;
		while (i < teams) {
			players = scanner.nextShort();
			teamA = new byte[players];
			j = 0;
			while (j < players) {
				teamA[j++] = scanner.nextByte();
			}
			j = 0;
			short aScore = 0, bScore = 0;
			short teamBPoints;
			while (j < players) {
				teamBPoints = scanner.nextByte();
				if (teamA[j] > teamBPoints)
					aScore++;
				else if (teamA[j] < teamBPoints)
					bScore++;
				j++;
			}
			i++;
			scores.append(aScore + " " + bScore + "\n");
		}
		scanner.close();
		System.out.println(scores);
	}

}
