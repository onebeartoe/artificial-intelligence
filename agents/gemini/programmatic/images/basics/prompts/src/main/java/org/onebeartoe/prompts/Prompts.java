
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
    
    public class Imaging
    {
        public static final String IMPRESSIONIST_OIL_PAINTING = """
                                        An impressionist oil painting
                                        of the port of La Rochelle
                                        with its towers and sailing ships.
                                        """;
        
        public class TapeRecoderUnwoundText
        {
            public static final String TAPE_RECODER_UNWOUND_TEXT = """
                                Cassette tape spilling out of a tape recorder to spell a word.
                                The word is in cursive handwritting.                                    
                                The word is 'Rhythm'.
                                                                   """;
        }
                        
        public class FunkyElectricChickenLeg
        {
            public static final String LITERAL = "funky electric chicken leg";
            
            
        }
    }
    
    public class Marketing
    {
        public static final String PRODUCT_LABEL = """
                                        take the image 
                                        and    create an old timey marketing label 
                                        in the fashion of Jack Daniels branding.  Don't actuall include Jack Daniels in the lable, just use the style.                                      
                                        center the guy's head in the image in the label.
                                                   
                                        The label is for a product with q-tips soaked 
                                        in kerosene for lighting camp fires.
                                                   """;
    }
    
    public class SportsBall
    {
        public final static String BEST_PLAYER = """
                What is the name of one prominant NBA player 
                currently playing for the %s?
                Provide just the first and last name.
                                """;
        
        
        
        public final static String NEXT_OPPONENT = """
                                        What team is next on the NBA San Antonio Spurs schedule.  
                                        Provide just the city name and mascot name.
                                """;
        
        public final static String NEXT_PIGEON = """
                            Add this pigeon head over the basketball player's head,
                            and make the pigeon head about the same size as the player's head.
                            """;
        

        /*
        Sample descriptive image prompt:
            An impressionist oil painting
            of the port of La Rochelle
            with its towers and sailing ships.        
        */        
        
        public final static String PLAYER_IMAGE = """                                      
                        A current photo 
                        of %s on the %s
                        with the basketball and correct team uniform.
                                """;
    }
}
