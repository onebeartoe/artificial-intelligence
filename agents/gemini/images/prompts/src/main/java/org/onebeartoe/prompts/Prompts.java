
package org.onebeartoe.prompts;

/**
 *
 */
public class Prompts 
{    
    public class Developer 
    {
        public class Testing 
        {
            public class Links 
            {
                public class Markdown
                {
                    public static final String INTERNAL_BROKEN_LINKS_REPORT = """
                            read all the readme.md files under this directory and subdirectories.  

                            look for any and all links.

                              discard any external links.  

                              external links are URLs that are not under this directory.  

                              give a report on any internal link that is broken.

                              the report includes a section with a file column and for each broken link. 

                              the report also includes a section with a table of broken links per file. 

                             the report ends with the total number of broken links.
                                                                                                                                                             
                                                                              """;
                }
            }
        }
    }
    
    public class Imaging
    {
        public static final String IMPRESSIONIST_OIL_PAINTING = """
                                        An impressionist oil painting
                                        of the port of La Rochelle
                                        with its towers and sailing ships.
                                        """;
        
        public class Combine
        {
            public static final String TAYTAY_CHANGE_DRESS_COLOR = """
                        Add this person to the exterior decor,
                        and make her wear the red dress.
                        """;
        }
          
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
//TODO: where is this used?        
        private static final String FUNKY_LITTLE_BEAT = " funky little beat that you can bug out to with an upbeat mood";
        
//TODO: uppercase the l in LYRICS        
        public static final String ALBUM_COVER_WITH_lYRICS = """
                         Create an image from these lyrics from the song 
                         'Imagine' from John Lennon.
                              
                              Limit the the text to two lines.
                              
                              Use a extra super large font size for the text.
                              
                              Use 4:3 for the image apect ratio.
                              
                              Here are the lyrics: %s"
                         """;
        
        public class Silly
        {
            public static final String HOW_SILLY_CAN_YOU_GET_IMAGE_DESCRIPTION = """
                            Create an image of a sunny daytime beach scene with lots of folks.
                            There are beach umbrellas standing in the sand.
                            Most of the foks have a surf board and there are many people
                            with large oversized surfboards surfing in the ocean.
                            There is some grassy sand hills near the scene.
                                                                                  """;
            
            public static final String HOW_SILLY_CAN_YOU_GET_LYRICS = """
An uplifting song with surf guitar rifts about silliness.

The lyrics should include:                                    
                            How silly can you get, passing away the time?
                                                                
                            Oh yeah!

                            It is wear your pants with one leg cut short day.

                            Go surfing with a shotgun!

                            Oh yeah!                                                                
                                                                """;
        }
    }
    
    public class RoboPizza
    {
        public static final String IMAGE = """
                                           A delicious-looking pizza, close-up, micro-world, 
                                           with cute little robot workers building the pizza.
                                           """;
        
        public static final String ANIMATION = """
                                                Make these robots animated 
                                                and finish spreading cheese and other ingredients.  
                                                then show them leave the pizza.  
                                                then show the pizza after it is cooked.
                                                """;
    }
    
    public class SportsBall
    {
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
        
        public final static String SWAP_PLAYER_HEAD = """
                                                    Add this pigeon head over the basketball player's 
                                                          head,
                                                    and make the pigeon head about the same size as the player's head.
                                                    """;
    }
    
    public class Video
    {
        public static final String DANCING_CHIHUAHUAS = 
                
                
                "how is your day?";
                
                
                
/*
                """
                Use the photos of the two chihuahuas in this directory to generate a video.
                                                         
                Notice that there are two distinct chihuahaus in the photos.
                                                         
                One is mostly black and the other is black and white.
                                                         
                The video shows the two chihuahuas dancing upright in a slow dance.
                                                         
                The background music is a Mexican folk song.
                                                         """;
*/        
        
        
        
        public static final String GORILLA_ON_THE_RIVER_WALK = """
            Generate a video of a gorilla walking on the San Antonio River Walk.

            The gorilla is a nice one and and is not intimidating at all.

            The gorilla is wearing a yellow guayabera with a banana image on the back.

            The gorilla is waking and then gets into a boat and starts paddling in the river.

            The gorillla only makes cute noises and farts at the end of the video.

            Be sure to show the gorilla turn around and smile at the camera at the end of the video.

            Have playful music playing in the background.                                                               
                                                               """;
        
        public class Editing
        {
//            public static final String 
            
            private static final String ADD_BACKGROUND_MUSIC_TO_LOOPED_VIDEO = """
Use this file as audio input content:
            /home/luke/Versioning/owner/github/artificial-intelligence/agents/gemini/music/silly-surf-song-1775514478035.mp3
                                                                               
Use this file as video input content:
            /home/luke/Versioning/owner/github/artificial-intelligence/agents/gemini/videos/create/silly-video-2026-04-07-0623-a.mp4
                                                                                                                                                                                                                                                                                                                                                                                                      
Do not change the audio or video input content in any way.
Use the audio content as background music for the video content.
Loop the video content until it reaches the length of audio content.
Save the new video in the current directory.
                                                                               """;
            
            /**
             * This prompt assumes the Music and Video file are also in the request 
             * content.
             * @return 
             */            
            public static String addBackgroundMusicToLoopedVideo()
            {
                var version = 5;
                
                System.out.println(Editing.class.getName() + " version: " + version);
                
                return ADD_BACKGROUND_MUSIC_TO_LOOPED_VIDEO;
            }
        }
    }
}
