
package org.onebeartoe.prompts;

/**
 *
 */
public class Prompts 
{
    // Sportsball
    public static final String nextGame = "who do the san antonio Spurs play next in the 2025 season?";
    public static final String bestPlayer = "Who is the best player on the San Antonio Spurs team?  Provide just the first and last as a response.";

// Generate an image of the current best player for the San Antonio Spurs.     
// Generate an image of the current best player for the Atlanta Hawks.
    
    public static void main(String[] args) 
    {
        System.out.println("Hello World!");
    }
    
    public class SportsBall
    {
        public final static String BEST_PLAYER = """
                What is the name of one prominant NBA player 
                currently playing for the %s?
                Provide just the first and last name.
                                """;
        
        public final static String PLAYER_IMAGE = """                                      
                        A current photo 
                        of %s on the %s
                        with the basketball and correct team uniform.
                                """;
        
        public final static String NEXT_PIGEON = """
                            Add this pigeon head over the basketball player's head,
                            and make the pigeon head about the same size as the player's head.
                            """;
    }
}
