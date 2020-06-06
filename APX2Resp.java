import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

// chamada de teste: java APX2Resp matrizes.txt

public class APX2Resp {

    public static void main(String[] args) throws IOException {

    String[] tipoM = {"","","","","","","","",""};

    String dados = Files.readString(Paths.get(args[0]));

    dados = dados.replaceAll("[^ 0-9]", " ");
    dados = dados.replaceAll(" {2,}", " ");

    String[] listaStr;
    listaStr = dados.split(" ");
    int tamLista = listaStr.length;

    int[] lista = new int[tamLista];

    for (int cont = 0; cont < tamLista; cont++) {
      lista[cont] = Integer.parseInt(listaStr[cont]);
    }

    int m = 0;
    int Lin, Col;

    int i = 0;
    while (i < tamLista - 1) {

      Lin = lista[i];
      i++;
      Col = lista[i];

      System.out.println("==== " + Lin + "-" + Col + " ====");

      int[][][] matriz = new int[m+1][Lin][Col];

      for (int l = 0; l < Lin; l++) {
        for (int c = 0; c < Col; c++) {
          i++;
          matriz[m][l][c] = lista[i];
          System.out.print(matriz[m][l][c] + " ");
        }
        //i++;
        System.out.println("");
      }
      System.out.print("");
      tipoM[m] = analisaMatriz(matriz, m, Lin, Col);
        System.out.println(tipoM[m]);
        System.out.println("------------------------------\r\n");
      i++;
      m++;
    }
  }

  public static String analisaMatriz (int[][][] matr, int nm, int l, int c) {
      int i, j;
      String resp = "";
      boolean simetrica = true;
      boolean identidade = true;

      if (l == 1 && c > 1) {
        resp = resp + "- Linha" + " ";
      }
      if (c == 1 && l > 1) {
        resp = resp + "- Coluna" + " ";
      }
    if (l == c && l > 1) {
      resp = resp + "- Quadrada" + " ";
    }
      if (l == 1 && c == 1 && matr[nm][l-1][c-1] ==1) {
        resp = resp + "- Hadamard" + ", H(1) ";
      }

      if(l==c) {
          for (i = 0; i < l; i++) {
              for (j = 0; j < c; j++) {
                  if (matr[nm][i][j] != matr[nm][j][i]) {
                      simetrica = false;
                  }
              }
          }
          if (simetrica) {
              resp = resp + "- Simétrica" + " ";
          }

          for (i = 0; i < l; i++) {
              for (j = 0; j <= i; j++) {
                  if (matr[nm][i][j] != 1 && i == j) {
                      identidade = false;
                  } else if (matr[nm][i][j] != 0 && i != j) {
                      identidade = false;
                  }
              }
          }
          if (identidade) {
              resp = resp + "- Identidade" + " ";
          }
      }

      // triangular: time is over
      // Hadamard, n>1: time is over
      // NRA: time is over

      return resp;
    }

  }