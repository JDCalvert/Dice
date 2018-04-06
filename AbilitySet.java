import java.util.Random;
import java.util.Arrays;
import java.util.Collections;

public class AbilitySet
{
	private final static int numRolls = 100;
	private final static int numAbilities = 6;

	public String abilities = "";

	public AbilitySet() {}
	
	public static AbilitySet rollAbilities(Random rand)
	{		
		AbilitySet abilitySet = new AbilitySet();
		abilitySet.rollAbilityScores(rand);
		
		return abilitySet;
	}
	
	private void rollAbilityScores(Random rand)
	{
		Integer[] integers = new Integer[numAbilities];
		
		for (int i=0; i<numAbilities; i++)
		{
			int score = rollAbilityScore(rand);
			Integer integer = Integer.valueOf(score);
			integers[i] = integer;
		}
		
		Arrays.sort(integers, Collections.reverseOrder());
		
		for (int i=0; i<numAbilities; i++)
		{
			int score = integers[i];
			
			if (i>0) abilities += ",";
			if (score < 10) abilities += " ";
			
			abilities += integers[i];
		}
	}
	private int rollAbilityScore(Random rand)
	{		
		Integer[] rolls = new Integer[numRolls];
		for (int i=0; i<numRolls; i++)
		{
			rolls[i] = rand.nextInt(6) + 1;
		}
		
		Arrays.sort(rolls, Collections.reverseOrder());
		
		int total = 0;
		for (int i=0; i<3; i++)
		{
			total += rolls[i];
		}
		
		return total;
	}
	
}