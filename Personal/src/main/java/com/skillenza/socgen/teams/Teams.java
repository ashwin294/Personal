package com.skillenza.socgen.teams;

import java.util.Scanner;

public class Teams {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		short matches = scanner.nextShort();
		scanner.nextLine();
		short teams = (short) (matches / 2);
		StringBuilder scores = new StringBuilder();
		short i = 0, j;
		byte[] teamA, teamB;
		short players;
		while (i < teams) {
			players = scanner.nextShort();
			scanner.nextLine();
			teamA = new byte[players];
			teamB = new byte[players];
			j = 0;
			while (j < players) {
				teamA[j++] = scanner.nextByte();
			}
			j = 0;
			scanner.nextLine();
			while (j < players) {
				teamB[j++] = scanner.nextByte();
			}
			i++;
			scores.append(compare(teamA, teamB));
			scanner.nextLine();
		}
		scanner.close();
		System.out.println(scores);
	}

	private static String compare(byte[] teamA, byte[] teamB) {
		short aScore = 0, bScore = 0;
		for (short j = 0; j < teamA.length; j++) {
			if (teamA[j] > teamB[j])
				aScore++;
			else if (teamA[j] < teamB[j])
				bScore++;
		}
		return aScore + " " + bScore + "\n";
	}

}
