package javatomysql;

import java.sql.*;
import java.util.Scanner;

public class JavaToMYSQL {
    static Connection conn;
    static Statement stmt;
    final static Scanner input = new Scanner(System.in);

    static void tampil_barang() throws SQLException {
        try {
            conn = koneksiMysql.getDBConnection();
            conn.setAutoCommit(false);
            String query = "SELECT * FROM barang";
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            int i = 0;
            System.out.println("==================================================================================================");
            System.out.println("Dafar Barang");
            System.out.println("==================================================================================================");
            while (resultSet.next()) {
                System.out.println("Kode Barang : " + resultSet.getString(1));
                System.out.println("Nama Barang : " + resultSet.getString(2));
                System.out.println("Kategori : " + resultSet.getString(3));
                System.out.println("Harga beli : " + resultSet.getString(4));
                System.out.println("Harga jual : " + resultSet.getString(5));
                System.out.println("Stok : " + resultSet.getString(6));
                System.out.println("==================================================================================================");
                ++i;
            }
            System.out.println("Jumlah Barang : " + i);
            System.out.println("==================================================================================================");
        } catch (SQLException exception) {
            System.out.println(exception);
        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }

    //    buat method untuk mencari dan menampilkan barang berdasarkan kode barang
    static void cari_kode(String kode) {
        try {
            conn = koneksiMysql.getDBConnection();
            conn.setAutoCommit(false);
            String query = "SELECT * FROM barang WHERE kode = '" + kode + "'";
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            int i = 0;
            System.out.println("==================================================================================================");
            System.out.println("Dafar Barang");
            System.out.println("==================================================================================================");
            while (resultSet.next()) {
                System.out.println("==================================================================================================");
                System.out.println("Kode Barang : " + resultSet.getString(1));
                System.out.println("Nama Barang : " + resultSet.getString(2));
                System.out.println("Kategori : " + resultSet.getString(3));
                System.out.println("Harga beli : " + resultSet.getString(4));
                System.out.println("Harga jual : " + resultSet.getString(5));
                System.out.println("Stok : " + resultSet.getString(6));
                System.out.println("==================================================================================================");
                ++i;
            }
            System.out.println("Jumlah Barang : " + i);
            System.out.println("==================================================================================================");
        } catch (SQLException exception) {
            System.out.println(exception);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException exception) {
                    System.out.println(exception);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException exception) {
                    System.out.println(exception);
                }
            }
        }
    }

    //    buat method untuk mencari dan menampilkan barang berdasarkan nama barang
    static void cari_nama(String nama) {
        try {
            conn = koneksiMysql.getDBConnection();
            conn.setAutoCommit(false);
            String query = "SELECT * FROM barang WHERE nama like '%" + nama + "%'";
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            int i = 0;
            System.out.println("==================================================================================================");
            System.out.println("Dafar Barang");
            System.out.println("==================================================================================================");
            while (resultSet.next()) {
                System.out.println("==================================================================================================");
                System.out.println("Kode Barang : " + resultSet.getString(1));
                System.out.println("Nama Barang : " + resultSet.getString(2));
                System.out.println("Kategori : " + resultSet.getString(3));
                System.out.println("Harga beli : " + resultSet.getString(4));
                System.out.println("Harga jual : " + resultSet.getString(5));
                System.out.println("Stok : " + resultSet.getString(6));
                System.out.println("==================================================================================================");
                ++i;
            }
            System.out.println("Jumlah Barang : " + i);
            System.out.println("==================================================================================================");
        } catch (SQLException exception) {
            System.out.println(exception);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException exception) {
                    System.out.println(exception);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException exception) {
                    System.out.println(exception);
                }
            }
        }
    }

    public static void main(String[] args) {
        int opsi;
        String kode, nama;
        do {
            System.out.println("==================================================================================================");
            System.out.println("Aplikasi Penjualan Barang");
            System.out.println("==================================================================================================");
            System.out.println("1. Tampilkan Barang");
            System.out.println("2. Cari Barang Berdasarkan Kode Barang");
            System.out.println("3. Cari Barang Berdasarkan Nama Barang");
            System.out.println("4. Keluar");
            System.out.println("==================================================================================================");
            System.out.print("Pilih Opsi : ");
            opsi = input.nextInt();
            switch (opsi) {
                case 1:
                    try {
                        tampil_barang();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    } finally {
                        System.out.println("\nTekan Enter untuk kembali ke menu");
                        input.nextLine();
                    }
                    break;
                case 2:
                    try {
                        input.nextLine();
                        System.out.print("Input Kode Barang : ");
                        kode = input.nextLine();
                        cari_kode(kode);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    } finally {
                        System.out.println("\nTekan Enter untuk kembali ke menu");
                        input.nextLine();
                    }
                    break;
                case 3:
                    try {
                        input.nextLine();
                        System.out.print("Input Nama Barang : ");
                        nama = input.nextLine();
                        cari_nama(nama);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    } finally {
                        System.out.println("\nTekan Enter untuk kembali ke menu");
                        input.nextLine();
                    }
                    break;
                case 4:
                    System.out.println("==================================================================================================");
                    System.out.println("Terima Kasih");
                    System.out.println("==================================================================================================");
                    break;
                default:
                    System.out.println("==================================================================================================");
                    System.out.println("Opsi yang anda pilih tidak tersedia");
                    System.out.println("==================================================================================================");
                    break;
            }
        } while (opsi != 4);
    }
}