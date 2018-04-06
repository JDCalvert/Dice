import java.util.*;

public class DiceRoller
{
	private static final int numRolls = 1000000;
	private static final int numToPrint = 20;
	//private static final String searchSet = "15,14,13,12,10, 8";
	//private static final String searchSet = "13,12,11,10, 9, 8";
	private static final String searchSet = "18,18,18,18,18,18";
	
	public static void main(String[] args)
	{
		System.out.println("Rolling " + numRolls + " times");
		
		//First, roll the results and group similar results together
		HashMap<String, Integer> abilitySetToNumber = generateAbilitySets();
		
		Integer numberOfSearchSet = abilitySetToNumber.getOrDefault(searchSet, Integer.valueOf(0));
		System.out.println(searchSet + " was rolled " + numberOfSearchSet + " times!");
		
		int numDistinctAbilitySets = abilitySetToNumber.size();
		System.out.println("We have " + numDistinctAbilitySets + " unique combinations");
		
		//Next, reverse the map so we can get a ranking
		HashMap<Integer, Vector<String>> numberToAbilitySets = reverseAbilitySetToNumber(abilitySetToNumber);
		
		int numDistinctRanks = numberToAbilitySets.size();
		System.out.println("We have " + numDistinctRanks + " distinct ranks of ability sets");
		
		Integer[] numbers = getNumbersArray(numberToAbilitySets);
		
		int totalListed = 0;
		for (int i=0; i<numToPrint; i++)
		{
			Integer number = numbers[i];
			Vector<String> abilitySets = numberToAbilitySets.get(number);
			
			for (String abilitySet: abilitySets)
			{
				System.out.println(number + ": " + abilitySet);
				totalListed++;
				if (totalListed >= numToPrint) break;
			}
		}
	}
	
	private static HashMap<String, Integer> generateAbilitySets()
	{
		Random rand = new Random();
		HashMap<String, Integer> abilitySetToNumber = new HashMap<String, Integer>();
		
		for (int i=0; i<numRolls; i++)
		{
			AbilitySet abilitySet = AbilitySet.rollAbilities(rand);
			String abilities = abilitySet.abilities;
			
			Integer number = abilitySetToNumber.get(abilities);
			if (number == null)
			{
				number = Integer.valueOf(0);
			}
			
			int value = number.intValue();
			value++;
			
			number = Integer.valueOf(value);
			
			abilitySetToNumber.put(abilities, number);		
		}
		
		return abilitySetToNumber;
	}
	
	private static HashMap<Integer, Vector<String>> reverseAbilitySetToNumber(HashMap<String, Integer> abilitySetToNumber)
	{
		HashMap<Integer, Vector<String>> numberToAbilitySets = new HashMap<Integer, Vector<String>>();
		
		for (Iterator<Map.Entry<String, Integer>> i = abilitySetToNumber.entrySet().iterator(); i.hasNext(); )
		{
			Map.Entry<String, Integer> entry = i.next();
			String abilitySet = entry.getKey();
			int number = entry.getValue();
			
			Vector<String> abilitySetsForRank = numberToAbilitySets.get(number);
			if (abilitySetsForRank == null)
			{
				abilitySetsForRank = new Vector<String>();
				numberToAbilitySets.put(number, abilitySetsForRank);
			}
			abilitySetsForRank.add(abilitySet);
		}
		
		return numberToAbilitySets;
	}
	
	private static Integer[] getNumbersArray(HashMap<Integer, Vector<String>> numberToAbilitySets)
	{
		Set<Integer> numbersSet = numberToAbilitySets.keySet();
		
		Integer[] numbers = new Integer[numbersSet.size()];		
		int index = 0;
		for (Integer i : numbersSet)
		{
			numbers[index++] = i;
		}
		
		Arrays.sort(numbers, Collections.reverseOrder());
		
		return numbers;
	}
}