// Chapter 7 : OOP Practice
public enum MediaRanking {

    DVD(0),
    Book(1),
    CD(2),
    MP3(3);

    private final int value;

    MediaRanking(final int _value){
        this.value = _value;
    }

    public int getValue() {
        return value;
    }
}

abstract class Media implements Comparable<Media>{

    protected String title;
    public String getTitle() {
        return title;
    }

    private int getMediaRanking(Media media){
        try {
            return MediaRanking.valueOf(media.getClass().getSimpleName()).getValue();
        } catch (IllegalArgumentException e) {
            return -1; // default rank
        }
    }

    @Override
    public int compareTo(Media media) {
        return getMediaRanking(this) - getMediaRanking(media);
    }
}

public class Book extends Media {


    private String author;

    public Book(String _title, String _author) {
        this.title = _title;
        this.author = _author;
    }

    public String getAuthor() {
        return author;
    }

    public String toString() {
        return getTitle() + " by " + getAuthor();
    }

    @Override
    public int compareTo(Media media) {
        if (media instanceof Book) {
            Book book = (Book) media;
            final int titleCompareTo = this.getTitle().compareToIgnoreCase(book.getTitle());
            return titleCompareTo == 0 ? this.getAuthor().compareToIgnoreCase(book.getAuthor()) : titleCompareTo;
        }
        return super.compareTo(media);
    }
}

public class CD extends Media {
    private String artist;
    private int year;

    public CD(String _title, String _artist, int _year) {
        this.title = _title;
        this.artist = _artist;
        this.year = _year;
    }

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return getTitle() + " by " + getArtist() + " released in " + getYear() + " [CD]";
    }

    @Override
    public int compareTo(Media media) {
        if (media instanceof CD) {
            CD cd = (CD) media;
            final int titleCompareTo = this.getTitle().compareToIgnoreCase(cd.getTitle());
            final int artistCompareTo = titleCompareTo == 0 ? this.getArtist().compareToIgnoreCase(cd.getArtist()) : titleCompareTo;
            return artistCompareTo == 0 ? this.getYear() - cd.getYear() : artistCompareTo;
        }
        return super.compareTo(media);
    }
}

public class DVD extends Media {
    private int year;

    public DVD(String _title, int _year) {
        this.title = _title;
        this.year = _year;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return year + ": " + title + " [DVD]";
    }

    @Override
    public int compareTo(Media media) {
        if (media instanceof DVD){
            DVD dvd = (DVD) media;
            final int titleCompareTo = this.getTitle().compareToIgnoreCase(dvd.getTitle());
            return titleCompareTo == 0 ? this.getYear() - dvd.getYear() : titleCompareTo;
        }
        return super.compareTo(media);
    }
}

public class MP3 extends Media {
    private String artist;
    private int year;
    private int bitrate; // in kbps

    public MP3(String _title, String _artist, int _year, int _bitrate) {
        this.title = _title;
        this.artist = _artist;
        this.year = _year;
        this.bitrate = _bitrate;
    }

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return year;
    }

    public int getBitrate() {
        return bitrate;
    }

    public String toString() {
        return getTitle() + " by " + getArtist() + " released in " + getYear() +  " (" + getBitrate() + " Kbps) [MP3]";
    }

    @Override
    public int compareTo(Media media) {
        if (media instanceof MP3) {
            MP3 mp3 = (MP3) media;
            final int titleCompareTo = this.getTitle().compareToIgnoreCase(mp3.getTitle());
            final int artistCompareTo = titleCompareTo == 0 ? this.getArtist().compareToIgnoreCase(mp3.getArtist()) : titleCompareTo;
            final int bitrateCompareTo = artistCompareTo == 0 ? this.getBitrate() - mp3.getBitrate() : artistCompareTo;
            return bitrateCompareTo == 0 ? this.getYear() - mp3.getYear() : bitrateCompareTo;
        }
        return super.compareTo(media);
    }
}

public class Main {

    public static void main(String[] args) {
        ArrayList<Media> library = new ArrayList<Media>(); // polymorphism
        library.add(new Book("Dare to go Solo", "Elizabeth Boese"));
        library.add(new DVD("Dare to go Solo", 2006));
        library.add(new DVD("Dare to go Solo", 1999));
        library.add(new Book("Intro to Programming with Java Applets", "Elizabeth Boese"));
        library.add(new DVD("Intro to Programming", 2011));
        library.add(new DVD("Intro to Programming", 1970));
        library.add(new CD("The Black Album", "Jay-Z", 2004));
        library.add(new DVD("Dare to go Solo", 2012));
        library.add(new DVD("Dare to go Solo", 2000));
        library.add(new CD("Beyonce", "Beyonce", 2013));
        library.add(new CD("Lemonade", "Beyonce", 2016));
        library.add(new CD("The Black Album", "Jay-Z", 2003));
        library.add(new CD("The Black Album", "Prince", 1994));
        library.add(new Book("Dare to go Solo", "Wise Travelguy"));
        library.add(new MP3("Beyonce", "Beyonce", 2013, 192));
        library.add(new MP3("Lemonade", "Beyonce", 2016, 192));
        library.add(new MP3("The Black Album", "Jay-Z", 2003, 192));
        library.add(new MP3("The Black Album", "Jay-Z", 2003, 320));
        library.add(new MP3("The Black Album", "Jay-Z", 2003, 4608));
        library.add(new MP3("The Black Album", "Prince", 1994, 192));
        library.add(new Book("Intro to Programming with Java Applets", "Elizabeth Boe"));
        Collections.sort(library);
        for (Object media : library) System.out.println(media);
    }
}
