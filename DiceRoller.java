import java.util.*;

public class DiceRoller
{
	public static void main(String[] args)
	{
		int numRolls = 100000;
		
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
		
		int numDistinctAbilitySets = abilitySetToNumber.size();
		System.out.println("We have " + numDistinctAbilitySets + " unique combinations");
		
		HashMap<Integer, Vector<String>> numberToAbilitySets = new HashMap<Integer, Vector<String>>();
		Set<String> abilitySets = abilitySetToNumber.keySet();
		
		for (String abilitySet : abilitySets)
		{
			Integer number = abilitySetToNumber.get(abilitySet);
			
			Vector<String> abilitySetsForRank = numberToAbilitySets.get(number);
			if (abilitySetsForRank == null)
			{
				abilitySetsForRank = new Vector<String>();
				numberToAbilitySets.put(number, abilitySetsForRank);
			}
			abilitySetsForRank.add(abilitySet);
		}
		
		int numDistinctRanks = numberToAbilitySets.size();
		System.out.println("We have " + numDistinctRanks + " distinct ranks of ability sets");
	}
}