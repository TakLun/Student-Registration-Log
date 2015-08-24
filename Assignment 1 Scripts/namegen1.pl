#!/usr/bin/env perl

use strict;
use warnings;

my $num_records = $ARGV[0];
my $outfile = $ARGV[1];
my $range = 899;
my $minimum = 100;

sub random {
    my ( $min, $max ) = @_;
    return int( rand( $max - $min ) ) + $min;
}

sub random_item_from {
    return @_[ rand @_ ];
}

sub random_consonant {
    return random_item_from(
        qw(b c d f g h j k l ll m n p q r s ss t v w x y z));
}

sub random_vowel {
    return random_item_from(qw(a e ee i o oo u));
}

sub random_word_generator {
    my $length = shift;
    my $word   = '';
    my $vowel  = random( 0, 2 );
    while ( length($word) < $length ) {
        $word .= $vowel ? random_vowel() : random_consonant();
        $vowel = !$vowel;
    }
    return $word;
}

sub write_to_file {
    my ( $stdent_first_name, $stdent_last_name, $prof_last_name, $course_number ) = @_;
    #sprintf( "%s %s %s %d", $stdent_first_name, $stdent_last_name, $prof_last_name, $course_number );
    open (MYFILE, ">>$outfile") or die $!;
    printf MYFILE ("%s %s %s %d\n", $stdent_first_name, $stdent_last_name, $prof_last_name, $course_number);
    close (MYFILE); 
}

sub random_name_generator {
    my $stdent_first_name  = random_word_generator( random( 3, 10 ) );
    my $stdent_last_name = random_word_generator( random( 3, 10 ) );
    my $prof_last_name = random_word_generator( random( 3, 10 ) );
    my $course_number = int(rand($range)) + $minimum;
    write_to_file( $stdent_first_name, $stdent_last_name, $prof_last_name, $course_number );
}

srand();

for ( 0 .. $num_records ) {
    random_name_generator();
}

