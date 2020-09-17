import java.util.Arrays;
import java.util.Random;
import java.util.Collections;
import java.util.List;

public class DigitoyOkey {

	/*Merge sort has taken from geeksforgeeks.org*/
	static void merge(int arr[], int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];

		/* Copy data to temp arrays */
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	// Main function that sorts arr[l..r] using
	// merge()
	static void sort(int arr[], int l, int r) {
		if (l < r) {
			// Find the middle point
			int m = (l + r) / 2;

			// Sort first and second halves
			sort(arr, l, m);
			sort(arr, m + 1, r);

			// Merge the sorted halves
			merge(arr, l, m, r);
		}
	}

	public static boolean IsContain(int[] tempPears, int numberToCheck)
	{
		for (int i = 0; i < tempPears.length; i++)
			if (tempPears[i] == numberToCheck)
				return true;
		return false;
	}
	
	public static boolean IsThreePairSameColor(int[] playersHand, int i)
	{
		if ((((playersHand[i] + 1) / 13) == ((playersHand[i+1] + 1) / 13)) && (((playersHand[i+1] + 1) / 13) == ((playersHand[i+2] + 1) / 13)))
			return true;
		return false;
	}
	
	public static boolean IsTwoPairSameColor(int[] playersHand, int i)
	{
		if (((playersHand[i] + 1) / 13) == ((playersHand[i+1] + 1) / 13))
			return true;
		return false;
	}

	public static boolean HaveOkey(int [] playerHand, int okey)
	{
		for(int i = 0; i < playerHand.length; i++)
			if(playerHand[i] == okey)
				return true;
		return false;
	}
	
	public static void StoreSameColorTiles(int[] playersHand, int[] tempPears, int okey, int counter)
	{
		for (int i = 0; i < playersHand.length; i++)
		{
			for (int j = i + 1; j < playersHand.length; j++)
			{
				if (((playersHand[i] + 1) % 13) == (playersHand[j] + 1) % 13)
					for(int k = j + 1; k < playersHand.length; k++)
					{
						if(playersHand[i] != okey && playersHand[j] != okey && playersHand[k] != okey)
							
							if((playersHand[k] % 13) == (playersHand[j] % 13))
								
								if (!IsContain(tempPears, playersHand[i]) && !IsContain(tempPears, playersHand[j]) && !IsContain(tempPears, playersHand[k]))
									
									if (playersHand[i] != playersHand[j] && playersHand[i] != playersHand[k] && playersHand[j] != playersHand[k])
									{
										tempPears[counter++] = playersHand[i];
										tempPears[counter++] = playersHand[j];
										tempPears[counter++] = playersHand[k];
									}
					}
			}
		}
	}
	
	static int playerNumber = 0;
	
	public static void CalculateRemainingTiles(int[] tempPears, int[] remainingTiles)
	{
		int countRemaining = 0;
		for (int i = 0; i < tempPears.length; i++)
			if(tempPears[i] == -1)
				countRemaining++;
		remainingTiles[playerNumber] = countRemaining;
		playerNumber++;
	}
	
	public static void CountRemainingTiles(int[] playersHand, int[] remainingTiles, int player, int okey) 
	{
		int i, j = 1, k = 2;
		int counter = 0;
		int[] tempPears = new int[playersHand.length];
		for(i = 0; i < playersHand.length; i++)
			tempPears[i] = -1;
		for (i = 0; i < playersHand.length - 3; i++)
		{
			if (playersHand[i] == playersHand[j] || playersHand[j] == playersHand[k])
			{
				j++;
				k++;
				continue;
			}
			if (playersHand[i] != okey && playersHand[j] != okey && playersHand[k] != okey) 
			{
				if (playersHand[i] + 2 == playersHand[j] + 1 && playersHand[j] + 1 == playersHand[k] && IsThreePairSameColor(playersHand, i))
				{
					if (!IsContain(tempPears, playersHand[i]) && !IsContain(tempPears, playersHand[j]) && !IsContain(tempPears, playersHand[k]))
					{
						tempPears[counter++] = playersHand[i];
						tempPears[counter++] = playersHand[j];
						tempPears[counter++] = playersHand[k];
					}
					if (!IsContain(tempPears, playersHand[k]))
						tempPears[counter++] = playersHand[k];
				}
			}
			j++;
			k++;
		}
		if (HaveOkey(playersHand, okey))
		{
			j = 1;
			int z;
			for (i = 0; i < playersHand.length - 2; i++)
			{
				if((playersHand[i] + 2 == playersHand[j] + 1) && IsTwoPairSameColor(playersHand, i))
				{
					if (!IsContain(tempPears, playersHand[i]) && !IsContain(tempPears, playersHand[j]) && !IsContain(tempPears, okey))
					{
						tempPears[counter++] = playersHand[i];
						tempPears[counter++] = playersHand[j];
						tempPears[counter++] = okey;
						k = j + 2;
						z = 2;
						while (playersHand[j] + z == playersHand[k])
						{
							tempPears[counter++] = playersHand[k];
							k++;
							z++;
						}
						j++;
					}
				}
				else {
				j++;
				k++;
				}
			}
			j = 2;
			for (i = 0; i < playersHand.length - 2; i++)
			{
				if((playersHand[i] + 2 == playersHand[j]) && IsTwoPairSameColor(playersHand, i))
					
					if (!IsContain(tempPears, playersHand[i]) && !IsContain(tempPears, playersHand[j]) && !IsContain(tempPears, okey))
					{
						tempPears[counter++] = playersHand[i];
						tempPears[counter++] = okey;
						tempPears[counter++] = playersHand[j];
					}
				j++;
			}
			if(IsContain(playersHand, okey) && !IsContain(tempPears, okey))
				tempPears[counter++] = okey;
		}
		
		StoreSameColorTiles(playersHand, tempPears, okey, counter);
		CalculateRemainingTiles(tempPears, remainingTiles);
	}

	public static int ChooseOkey(Integer[] tiles) {
		Random r = new Random();
		int randomlyChosenIndex = r.nextInt(106 - 58 + 1) + 58;
		int okey = tiles[randomlyChosenIndex] + 1;
		while (okey == 52) {
			randomlyChosenIndex = r.nextInt(106 - 58 + 1) + 58;
			okey = tiles[randomlyChosenIndex] + 1;
		}
		if ((tiles[randomlyChosenIndex] + 1) % 13 == 0)
			okey = tiles[randomlyChosenIndex] - 12;
		return okey;
	}

	public static void DistributeTilesToPlayers(Integer[] tiles, int[] firstPlayer, int[] secondPlayer, int[] thirdPlayer,
			int[] forthPlayer) {
		int i;
		int j = 0;
		thirdPlayer[0] = -1;
		forthPlayer[0] = -1;
		for (i = 0; i < 57; i++) {
			if (i < 15)
				firstPlayer[i] = tiles[i];
			else if (i < 29) {
				secondPlayer[j] = tiles[i];
				j++;
			} else if (i < 43) {
				if (thirdPlayer[0] == -1)
					j = 0;
				thirdPlayer[j] = tiles[i];
				j++;
			} else {
				if (forthPlayer[0] == -1)
					j = 0;
				forthPlayer[j] = tiles[i];
				j++;
			}
		}
	}

	public static void CreateShuffleTiles(Integer[] tiles) { //First assigns numbers in increasing order then shuffles.
		int i, tileNumber = 0;
		for (i = 0; i < tiles.length; i = i + 2) {
			tiles[i] = tileNumber;
			tiles[i + 1] = tileNumber;
			tileNumber++;
		}
		List<Integer> listToShuffle = Arrays.asList(tiles);
		Collections.shuffle(listToShuffle);
		listToShuffle.toArray(tiles);
	}

	public static void PrintArray(int[] array, String player) {
		System.out.print(player + " : ");
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + "-");
		System.out.println();
	}

	public static String FindPossibleWinner(int[] remainingTiles, int[] first, int[] second, int[] third, int[] forth)
	{
		int winner = 0;
		remainingTiles[0]--;			// First player has one more tile so, we need to decrease remaining tile number by 1.
		int tempWinner = remainingTiles[0];
		for (int i = 1; i < remainingTiles.length; i++)
		{
			if(remainingTiles[i] < tempWinner)
			{
				tempWinner = remainingTiles[i];
				winner = i;
			}
		}
		if (winner == 0)
		{
			PrintArray(first, "Possible winner's hand: ");
			return "First Player will win";
		}
		else if (winner == 1)
		{
			PrintArray(second, "Possible winner's hand: ");
			return "Second Player will win";
		}
		else if (winner == 2)
		{
			PrintArray(third, "Possible winner's hand: ");
			return "Third Player will win";
		}
		else if (winner == 3)
		{
			PrintArray(forth, "Possible winner's hand: ");
			return "Forth Player will win";
		}
		else
			return "Error";
	}
	
	public static void main(String[] args) {
		Integer[] tiles;
		tiles = new Integer[106];
		int[] remainingTiles;
		remainingTiles = new int[4];
		int[] firstPlayer;
		firstPlayer = new int[15];
		int[] secondPlayer;
		secondPlayer = new int[14];
		int[] thirdPlayer;
		thirdPlayer = new int[14];
		int[] forthPlayer;
		forthPlayer = new int[14];
		CreateShuffleTiles(tiles);
		int okey = ChooseOkey(tiles);
		DistributeTilesToPlayers(tiles, firstPlayer, secondPlayer, thirdPlayer, forthPlayer);
		sort(firstPlayer, 0, firstPlayer.length - 1);
		sort(secondPlayer, 0, secondPlayer.length - 1);
		sort(thirdPlayer, 0, thirdPlayer.length - 1);
		sort(forthPlayer, 0, forthPlayer.length - 1);
		CountRemainingTiles(firstPlayer, remainingTiles, 0, okey);
		CountRemainingTiles(secondPlayer, remainingTiles, 1, okey);
		CountRemainingTiles(thirdPlayer, remainingTiles, 2, okey);
		CountRemainingTiles(forthPlayer, remainingTiles, 3, okey);
		String possibleWinner = FindPossibleWinner(remainingTiles, firstPlayer, secondPlayer, thirdPlayer, forthPlayer);
		System.out.println();
		System.out.println(possibleWinner);
	}

}
