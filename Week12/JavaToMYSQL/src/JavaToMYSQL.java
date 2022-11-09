package javatomysql;

import java.sql.*;
import java.util.Scanner;

public class JavaToMYSQL {
    final static Scanner input = new Scanner(System.in);
    static Connection conn;
    static Statement stmt;

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

    //    buat method untuk menambahkan data barang
    static void insert_barang(String kode, String nama, String kategori, double hBeli, double hJual, int stok) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = koneksiMysql.getDBConnection();
            connection.setAutoCommit(false);
            String query = "Insert INTO barang (kode, nama, kategori, hargabeli, hargajual, stok) VALUES('" + kode + "', '" + nama + "', '" + kategori + "', '" + hBeli + "', '" + hJual + "', '" + stok + "')";
            statement = connection.prepareStatement(query);
            statement.execute();
            connection.commit();
        } catch (SQLException exception) {
            System.out.println("error " + exception);
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    System.out.println("error " + ex);
                }
            }

            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.out.println("error " + ex);
                }
            }
            System.out.println("Data Berhasil Disimpan!");
        }
    }

    //    buat method untuk mengubah data barang
    static void update_barang(String kode, String nama, String kategori, double hBeli, double hJual, int stok) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = koneksiMysql.getDBConnection();
            connection.setAutoCommit(false);
            String query = "UPDATE barang SET nama = '" + nama + "', kategori = '" + kategori + "', hargabeli = '" + hBeli + "', hargajual = '" + hJual + "', stok = '" + stok + "' WHERE kode = '" + kode + "'";
            statement = connection.prepareStatement(query);
            statement.execute();
            connection.commit();
        } catch (SQLException exception) {
            System.out.println("error " + exception);
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    System.out.println("error " + ex);
                }
            }

            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.out.println("error " + ex);
                }
            }
            System.out.println("Data Berhasil Diperbaharui!");
        }
    }

//    buat method untuk menghapus data
    static void delete_barang(String kode){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = koneksiMysql.getDBConnection();
            connection.setAutoCommit(false);
            String query = "DELETE FROM barang WHERE kode = '" + kode + "'";
            statement = connection.prepareStatement(query);
            statement.execute();
            connection.commit();
        } catch (SQLException exception) {
            System.out.println("error " + exception);
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    System.out.println("error " + ex);
                }
            }

            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.out.println("error " + ex);
                }
            }
            System.out.println("Data Berhasil Dihapus!");
        }
    }

    public static void main(String[] args) {
        int opsi, stok;
        String kode, nama, kategori;
        double hBeli, hJual;

        do {
            System.out.println("================================");
            System.out.println("========== MENU UTAMA ==========");
            System.out.println("================================");
            System.out.println("1. Insert Data");
            System.out.println("2. Show Data");
            System.out.println("3. Edit Data");
            System.out.println("4. Delete Data");
            System.out.println("0. Keluar");
            System.out.println("PILIHAN : ");
            System.out.println("================================");
            opsi = input.nextInt();
            if (opsi == 1) {
                try {
                    input.nextLine();
                    System.out.println("=========  INPUT DATA  =========");
                    System.out.println("Kode Barang\t: ");
                    kode = input.nextLine();
                    System.out.println("Nama Barang\t: ");
                    nama = input.nextLine();
                    System.out.println("Kategori\t: ");
                    kategori = input.nextLine();
                    System.out.println("Harga Beli\t: ");
                    hBeli = input.nextDouble();
                    System.out.println("Harga Jual\t: ");
                    hJual = input.nextDouble();
                    System.out.println("Stok");
                    stok = input.nextInt();
                    System.out.println();
                    System.out.println("================================");
                    insert_barang(kode, nama, kategori, hBeli, hJual, stok);
                    input.nextLine();
                } catch (Exception exception) {
                    System.out.println("error " + exception);
                } finally {
                    System.out.println("\nTekan Enter untuk kembali ke menu");
                    input.nextLine();
                }
            } else if (opsi == 2) {
                try {
                    tampil_barang();
                } catch (Exception ex) {
                    System.out.println(ex);
                } finally {
                    System.out.println("\nTekan Enter untuk kembali ke menu");
                    input.nextLine();
                }
                input.nextLine();
            } else if (opsi == 3) {
                try {
                    input.nextLine();
                    System.out.println("=========  UPDATE DATA  =========");
                    System.out.println("Kode Barang\t: ");
                    kode = input.nextLine();
                    System.out.println("Nama Barang\t: ");
                    nama = input.nextLine();
                    System.out.println("Kategori\t: ");
                    kategori = input.nextLine();
                    System.out.println("Harga Beli\t: ");
                    hBeli = input.nextDouble();
                    System.out.println("Harga Jual\t: ");
                    hJual = input.nextDouble();
                    System.out.println("Stok");
                    stok = input.nextInt();
                    System.out.println();
                    System.out.println("================================");
                    update_barang(kode, nama, kategori, hBeli, hJual, stok);
                    input.nextLine();
                } catch (Exception exception) {
                    System.out.println("error " + exception);
                } finally {
                    System.out.println("\nTekan Enter untuk kembali ke menu");
                    input.nextLine();
                }
            } else if (opsi == 4) {
                try {
                    input.nextLine();
                    System.out.println("=========  UPDATE DATA  =========");
                    System.out.println("Kode Barang\t: ");
                    kode = input.nextLine();
                    System.out.println();
                    System.out.println("================================");
                    delete_barang(kode);

                } catch (Exception exception) {
                    System.out.println("error " + exception);
                } finally {
                    System.out.println("\nTekan Enter untuk kembali ke menu");
                    input.nextLine();
                }
            } else if (opsi == 0) {
                System.out.println("================================");
                System.out.println("========== TERIMA KASIH =========");
                System.out.println("================================");
            } else {
                System.out.println("================================");
                System.out.println("========== MENU TIDAK ADA =========");
                System.out.println("================================");
            }
        } while (opsi != 0);
    }
}