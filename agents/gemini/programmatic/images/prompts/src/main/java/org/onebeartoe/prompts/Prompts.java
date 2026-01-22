
package org.onebeartoe.prompts;

/**
 *
 */
public class Prompts 
{    
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
                                        
                                        use "Old Man Beto's" as the top banner.
                                                   
                                        The label is for a product with q-tips soaked 
                                        in kerosene for lighting camp fires.
                                                   """;
    }

    public class Multimedia
    {
        public static String reduceFilesizeButNotQuality(String ... extentions)
        {
            String extentionsClause = null;
                    
            if(extentions.length == 1)
            {
                extentionsClause = extentions[0];
            }
            else
            {
                extentionsClause = String.join(" nor ", extentions);
            }            

            var prompt = String.format(REDUCE_FILESIZE_BUT_NOT_QUALITY, extentionsClause);
            
            return prompt;
        }
        
        /**
         * This works well if 'ffmpeg' is on the $PATH.
         */
         private static final String REDUCE_FILESIZE_BUT_NOT_QUALITY = """
            Do not change the existing %s files in any way.

            Create a new file for each existing one, that reduces the files size but does not reduce the audio, image, or video quality.

            List the new files.

            Report on size and quality reduction for each new file.
                                                                       """;
    }    
    
    public class Music
    {
        private static final String FUNKY_LITTLE_BEAT = " funky little beat that you can bug out to with an upbeat mood";
    }
    
    public class RoboPizza
    {
        public static final String IMAGE = """
                                           A delicious-looking pizza, close-up, micro-world, 
                                           with cute little robot workers building the pizza.
                                           """;
        
        private static final String ANIMATION = """
                                                Make these robots animated 
                                                and finish spreading cheese and other ingredients.  
                                                then show them leave the pizza.  
                                                then show the pizza after it is cooked.
                                                """;
    }
    
    public class SportsBall
    {
//TODO: ???        
// Generate an image of the current best player for the San Antonio Spurs.     
// Generate an image of the current best player for the Atlanta Hawks.        

//TODO: is this even used?        
public static final String nextGame = "who do the san antonio Spurs play next in the 2025 season?";
public static final String bestPlayer = "Who is the best player on the San Antonio Spurs team?  Provide just the first and last as a response.";
    
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
        


        
        public final static String PLAYER_IMAGE = """                                      
                        A current photo 
                        of %s on the %s
                        with the basketball and correct team uniform.
                                """;
    }
}
