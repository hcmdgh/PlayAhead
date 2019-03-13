package com.example.genghao.sokoban;

enum Status {space, undefined, box, wall, dest, box_dest}

public interface SokobanLevels {
    String[] levels = {
                "####\n" +
                "# .#\n" +
                "#  ###\n" +
                "#*@  #\n" +
                "#  $ #\n" +
                "#  ###\n" +
                "####\n",

                "######\n" +
                "#    #\n" +
                "# #@ #\n" +
                "# $* #\n" +
                "# .* #\n" +
                "#    #\n" +
                "######\n",

                "  ####\n" +
                "###  ####\n" +
                "#     $ #\n" +
                "# #  #$ #\n" +
                "# . .#@ #\n" +
                "#########\n",

                "########\n" +
                "#      #\n" +
                "# .**$@#\n" +
                "#      #\n" +
                "#####  #\n" +
                "    ####\n",

                " #######\n" +
                " #     #\n" +
                " # .$. #\n" +
                "## $@$ #\n" +
                "#  .$. #\n" +
                "#      #\n" +
                "########\n",

                "###### #####\n" +    // level 6
                "#    ###   #\n" +
                "# $$     #@#\n" +
                "# $ #...   #\n" +
                "#   ########\n" +
                "#####\n",

                "#######\n" +
                "#     #\n" +
                "# .$. #\n" +
                "# $.$ #\n" +
                "# .$. #\n" +
                "# $.$ #\n" +
                "#  @  #\n" +
                "#######\n",

                "  ######\n" +
                "  # ..@#\n" +
                "  # $$ #\n" +
                "  ## ###\n" +
                "   # #\n" +
                "   # #\n" +
                "#### #\n" +
                "#    ##\n" +
                "# #   #\n" +
                "#   # #\n" +
                "###   #\n" +
                "  #####",

                "#####\n" +
                "#.  ##\n" +
                "#@$$ #\n" +
                "##   #\n" +
                " ##  #\n" +
                "  ##.#\n" +
                "   ###\n",

                "      #####\n" +
                "      #.  #\n" +
                "      #.# #\n" +
                "#######.# #\n" +
                "# @ $ $ $ #\n" +
                "# # # # ###\n" +
                "#       #\n" +
                "#########",

                "  ######\n" +
                "  #    #\n" +
                "  # ##@##\n" +
                "### # $ #\n" +
                "# ..# $ #\n" +
                "#       #\n" +
                "#  ######\n" +
                "####",

                "#####\n" +
                "#   ##\n" +
                "# $  #\n" +
                "## $ ####\n" +
                " ###@.  #\n" +
                "  #  .# #\n" +
                "  #     #\n" +
                "  #######",

                "####\n" +
                "#. ##\n" +
                "#.@ #\n" +
                "#. $#\n" +
                "##$ ###\n" +
                " # $  #\n" +
                " #    #\n" +
                " #  ###\n" +
                " ####",

                "#######\n" +
                "#     #\n" +
                "# # # #\n" +
                "#. $*@#\n" +
                "#   ###\n" +
                "#####",

                "     ###\n" +
                "######@##\n" +
                "#    .* #\n" +
                "#   #   #\n" +
                "#####$# #\n" +
                "    #   #\n" +
                "    #####",

                " ####\n" +
                " #  ####\n" +
                " #     ##\n" +
                "## ##   #\n" +
                "#. .# @$##\n" +
                "#   # $$ #\n" +
                "#  .#    #\n" +
                "##########",

                "#####\n" +
                "# @ #\n" +
                "#...#\n" +
                "#$$$##\n" +
                "#    #\n" +
                "#    #\n" +
                "######",

                "#######\n" +   // level 18
                "#     #\n" +
                "#. .  #\n" +
                "# ## ##\n" +
                "#  $ #\n" +
                "###$ #\n" +
                "  #@ #\n" +
                "  #  #\n" +
                "  ####",

                "########\n" +
                "#   .. #\n" +
                "#  @$$ #\n" +
                "##### ##\n" +
                "   #  #\n" +
                "   #  #\n" +
                "   #  #\n" +
                "   ####",

                "#######\n" +
                "#     ###\n" +
                "#  @$$..#\n" +
                "#### ## #\n" +
                "  #     #\n" +
                "  #  ####\n" +
                "  #  #\n" +
                "  ####",

                "####\n" +
                "#  ####\n" +
                "# . . #\n" +
                "# $$#@#\n" +
                "##    #\n" +
                " ######",

                "#####\n" +
                "#   ###\n" +
                "#. .  #\n" +
                "#   # #\n" +
                "## #  #\n" +
                " #@$$ #\n" +
                " #    #\n" +
                " #  ###\n" +
                " ####",

                "#######\n" +
                "#  *  #\n" +
                "#     #\n" +
                "## # ##\n" +
                " #$@.#\n" +
                " #   #\n" +
                " #####",

                "# #####\n" +
                "  #   #\n" +
                "###$$@#\n" +
                "#   ###\n" +
                "#     #\n" +
                "# . . #\n" +
                "#######",

                " ####\n" +
                " #  ###\n" +
                " # $$ #\n" +
                "##... #\n" +
                "#  @$ #\n" +
                "#   ###\n" +
                "#####",

                " #####\n" +
                " # @ #\n" +
                " #   #\n" +
                "###$ #\n" +
                "# ...#\n" +
                "# $$ #\n" +
                "###  #\n" +
                "  ####",

                "######\n" +
                "#   .#\n" +
                "# ## ##\n" +
                "#  $$@#\n" +
                "# #   #\n" +
                "#.  ###\n" +
                "#####",

                "#####\n" +    // level 28
                "#   #\n" +
                "# @ #\n" +
                "# $$###\n" +
                "##. . #\n" +
                " #    #\n" +
                " ######",

                "     #####\n" +
                "     #   ##\n" +
                "     #    #\n" +
                " ######   #\n" +
                "##     #. #\n" +
                "# $ $ @  ##\n" +
                "# ######.#\n" +
                "#        #\n" +
                "##########",

                "####\n" +
                "#  ###\n" +
                "# $$ #\n" +
                "#... #\n" +
                "# @$ #\n" +
                "#   ##\n" +
                "#####",

                "  ####\n" +
                " ##  #\n" +
                "##@$.##\n" +
                "# $$  #\n" +
                "# . . #\n" +
                "###   #\n" +
                "  #####",

                " ####\n" +
                "##  ###\n" +
                "#     #\n" +
                "#.**$@#\n" +
                "#   ###\n" +
                "##  #\n" +
                " ####",

                "#######\n" +
                "#. #  #\n" +
                "#  $  #\n" +
                "#. $#@#\n" +
                "#  $  #\n" +
                "#. #  #\n" +
                "#######",

                "  ####\n" +
                "###  ####\n" +
                "#       #\n" +
                "#@$***. #\n" +
                "#       #\n" +
                "#########",

                "  ####\n" +    // level 35
                " ##  #\n" +
                " #. $#\n" +
                " #.$ #\n" +
                " #.$ #\n" +
                " #.$ #\n" +
                " #. $##\n" +
                " #   @#\n" +
                " ##   #\n" +
                "  #####",

                "####\n" +
                "#  ############\n" +
                "# $ $ $ $ $ @ #\n" +
                "# .....       #\n" +
                "###############",

                "      ###\n" +
                "##### #.#\n" +
                "#   ###.#\n" +
                "#   $ #.#\n" +
                "# $  $  #\n" +
                "#####@# #\n" +
                "    #   #\n" +
                "    #####",

                "##########\n" +
                "#        #\n" +
                "# ##.### #\n" +
                "# # $$ . #\n" +
                "# . @$## #\n" +
                "#####    #\n" +
                "    ######",

                "#####\n" +
                "#   ####\n" +
                "# # # .#\n" +
                "#    $ ###\n" +
                "### #$.  #\n" +
                "#   #@   #\n" +
                "# # ######\n" +
                "#   #\n" +
                "#####",

                " #####\n" +
                " #   #\n" +
                "##   ##\n" +
                "# $$$ #\n" +
                "# .+. #\n" +
                "#######",
    };
}
