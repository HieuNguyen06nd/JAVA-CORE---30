package manageFilm;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Movie[] movies = new Movie[0];
        Series[] series = new Series[0];

        while (true){
            System.out.println("Tạo 2 mảng để quản lý Movie và Series\n" +
                    "1. Nhap Movie\n"+
                    "2. Nhap Series\n"+
                    "3. In thông tin phim ra màn hình\n" +
                    "4. Nhập vào từ khoá tiêu đề film và tìm phim theo tiêu đề ( tìm cả movie và series, tìm theo từ khoá)\n" +
                    "5. In ra thông tin film có ranking thấp nhất (kiểm tra cả movie và series)\n" +
                    "6. n ra tên đạo diễn của thể loại COMEDY có ranking thấp nhất\n" +
                    "0. Thoat");
            System.out.println("chon chuc nang");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Mời b nhập số movie: ");
                    int n = Integer.parseInt(scanner.nextLine());
                    movies = new Movie[n];
                    for (int i = 0; i < n; i++) {
                        movies[i] = inputMovie(scanner);
                    }
                    break;
                case 2:
                    System.out.println("Mời b nhập số series: ");
                    n = Integer.parseInt(scanner.nextLine());
                    series = new Series[n];
                    for (int i = 0; i < n; i++) {
                        series[i] = inputSeries(scanner);
                    }
                case 3:
                    System.out.println("Thông tin các phim:");
                    for (Movie movie : movies) {
                        if (movie != null) {
                            System.out.println(movie);
                        }
                    }
                    System.out.println("Thông tin các series:");
                    for (Series serie : series) {
                        if (serie != null) {
                            System.out.println(serie);
                        }
                    }
                    break;
                case 4:
                    System.out.println("nhap key de tim kiem");
                    String key = scanner.nextLine();
                    searchTitle(key, movies, series);
                    break;
                case 5:
                    System.out.println("Film co rank thap nhat la: ");
                    Film rank = rankFilm(movies,series);
                    System.out.println(rank);
                    break;
                case 6:
                    printComedyRank(movies,series);
                    break;
                case 0:
                    System.exit(1);
                default:
                    System.out.println("lua chon k hop le");
            }
        }

//        System.out.println("Mời b nhập số movie: ");
//        int n = Integer.parseInt(scanner.nextLine());
//        Movie[] movies = new Movie[n];
//        for (int i = 0; i < n; i++) {
//            Movie movie = inputMovie(scanner);
//            movies[i] = movie;
//        }
//        for (int i = 0; i < n; i++) {
//            System.out.println(movies[i]);
//        }
//
//        System.out.println("Mời b nhập số series: ");
//         n = Integer.parseInt(scanner.nextLine());
//        Series[] series = new Series[n];
//        for (int i = 0; i < n; i++) {
//            Series serie = inputSeries(scanner);
//            series[i] = serie;
//        }
//        for (int i = 0; i < n; i++) {
//            System.out.println(series[i]);
//        }
//
//        System.out.println("nhap key de tim kiem");
//        String key = scanner.nextLine();
//        searchTitle(key, movies, series);
//
//        System.out.println("Film co rank thap nhat la: ");
//        Film rank = rankFilm(movies,series);
//
//        printComedyRank(movies,series);

    }
    public static Movie inputMovie(Scanner scanner){

        System.out.println("nhap id");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Nhap thoi luong phim : ");
        int time = Integer.parseInt(scanner.nextLine());

        System.out.println("nhap tieu de");
        String title = scanner.nextLine();

        System.out.println("chon the loai (HORROR, COMEDY, DOCUMENTARY");
        Category cate = Category.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("so danh gia");
        double ranking = Double.parseDouble(scanner.nextLine());

        return new Movie(id,title,cate,ranking,time);

    }
    public static Series inputSeries(Scanner scanner){

        System.out.println("nhap id");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Nhap so top phim : ");
        int episodeNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("nhap thoi luong tring binh");
        int averageDuration = Integer.parseInt(scanner.nextLine());

        System.out.println("nhap tieu de");
        String title = scanner.nextLine();

        System.out.println("chon the loai (HORROR, COMEDY, DOCUMENTARY");
        Category cate = Category.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("so danh gia");
        double ranking = Double.parseDouble(scanner.nextLine());

        return new Series(id,title,cate,ranking,episodeNumber,averageDuration);

    }

    public static void searchTitle( String key, Movie[] movies, Series[] series){
        boolean check = false;

        for (Movie movie : movies){
            if (movie != null && movie.getTitle().contains(key)){
                System.out.println(movie);
                check = true;
            }
        }

        for (Series series1 : series){
            if (series1 != null && series1.getTitle().contains(key)){
                System.out.println(series1);
                check = true;
            }
        }
        if (!check){
            System.out.println("Khong tim thay phim voi: "+ key);
        }
    }
    public static Film rankFilm(Movie[] movies, Series[] series) {
        Film rank = null;
        double minRanking = Double.MAX_VALUE;

        for (Series series1 : series) {
            if (series1 != null && series1.getRanking() < minRanking) {
                rank = series1;
                minRanking = series1.getRanking();
            }
        }

        for (Movie movie : movies) {
            if (movie != null && movie.getRanking() < minRanking) {
                rank = movie;
                minRanking = movie.getRanking();
            }
        }
        if (rank == null) {
            System.out.println("khong co phim.");
            return null;
        }
        return rank;
    }

    public static void printComedyRank(Movie[] movies, Series[] series){

        Film rank = null;
        double minRanking = Double.MAX_VALUE;

        for (Movie movie : movies){
            if (movie != null && movie.getCategory() == Category.COMEDY && movie.getRanking() < minRanking){
                rank = movie;
                minRanking = movie.getRanking();
            }
        }

        for (Series series1 : series){
            if (series1 != null && series1.getCategory() == Category.COMEDY && series1.getRanking() < minRanking){
                rank = series1;
                minRanking = series1.getRanking();
            }
        }

        if (rank != null) {
            System.out.println("Dao dien co phim thuoc cate:COMEDY co rank thap nhat la "+ rank.getTitle());
        }else {
            System.out.println("khong co phim");
        }

    }
}
