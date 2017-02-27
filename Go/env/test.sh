#!/bin/sh
dir=$pwd
mkdir a && cd a && touch a.txt
cd $dir && mkdir b && cd b && touch b.txt && $pwd

