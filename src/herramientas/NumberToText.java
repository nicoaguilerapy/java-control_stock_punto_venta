package herramientas;

public class NumberToText {

    String resultado = "", prefixmil = "";
    Integer u, d, c, um, dm, cm;

    public NumberToText() {

    }

    public String convertir(Integer entero) {
        resultado = "";
        u = entero % 10;
        entero = entero / 10;
        d = entero % 10;
        entero = entero / 10;
        c = entero % 10;

        entero = entero / 10;
        um = entero % 10;
        entero = entero / 10;
        dm = entero % 10;
        entero = entero / 10;
        cm = entero % 10;

        if (u != 0 && d != 1) {
            resultado = unidad(u);
            //System.out.println("Unidad: " + u + " Retorno " + unidad(u));
        }

        if (d != 0) {

            if (u == 0) {
                resultado = decenacon0(d) + resultado;
                //System.out.println("Decena: " + d + " Retorno " + decenacon0(d));
            } else {
                if (d == 1 && u != 0) {
                    Integer aux = (d * 10) + u;
                    resultado = decenasin0(aux) + resultado;
                    //System.out.println("Decena: " + d + " Retorno " + decenasin0(aux));
                }
                resultado = decena(d) + resultado;
                //System.out.println("Decena: " + d + " Retorno " + decena(d));
            }
        }

        if (c != 0) {

            if (c == 1 || c == 5 || c == 9) {
                resultado = centena(c) + resultado;
                //System.out.println("Centena: " + c + " Retorno " + centena(c));
            } else {
                resultado = unidad(c) + "cientos " + resultado;
                //System.out.println("Centana: " + c + " Retorno " + unidad(c));
            }
        }

        if (um != 0) {
            if (um != 1 && dm != 1) {
                resultado = unidad(um) + " mil " + resultado;
            } else {
                if (um == 1 && dm == 0) {
                    resultado = " mil " + resultado;
                }
            }
            //System.out.println("Unidad de mil: " + um + " Retorno " + unidad(um));
        }

        if (dm != 0) {

            if (um == 0) {
                resultado = decenacon0(dm) + " mil " + resultado;
                //System.out.println("Decena de mil: " + dm + " Retorno " + decenacon0(dm));
            } else {
                if (dm == 1 && um != 0) {
                    Integer aux = (dm * 10) + um;
                    resultado = decenasin0(aux) + " mil " + resultado;
                    //System.out.println("Decena de mil: " + dm + " Retorno " + decenasin0(aux));
                }
                resultado = decena(dm)+ resultado;
                //System.out.println("Decena de mil: " + dm + " Retorno " + decena(dm));
            }
        }

        if (cm != 0) {
            if(dm==0&&um==0){
            resultado = "mil " + resultado;
            }
            if (cm != 1) {
                resultado = unidad(cm) + "cientos " + resultado;
                //System.out.println("Centena de mil: " + cm + " Retorno " + unidad(cm));
            } else {
                resultado = "cien " + resultado;
                //System.out.println("Centena de mil: " + cm + " Retorno " + unidad(cm));
            }
            
            
        }
        return resultado;
    }

    private String unidad(Integer unidad) {

        switch (unidad) {
            case 0:
                return "";
            case 1:
                return "uno";
            case 2:
                return "dos";
            case 3:
                return "tres";
            case 4:
                return "cuatro";
            case 5:
                return "cinco";
            case 6:
                return "seis";
            case 7:
                return "siete";
            case 8:
                return "ocho";
            case 9:
                return "nueve";
            default:
                return "";
        }
    }

    private String decenacon0(Integer decena) {

        switch (decena) {
            case 1:
                return "diez";
            case 2:
                return "veinte";
            case 3:
                return "treinta";
            case 4:
                return "cuarenta";
            case 5:
                return "cincuenta";
            case 6:
                return "sesenta";
            case 7:
                return "setenta";
            case 8:
                return "ochenta";
            case 9:
                return "noventa";
            default:
                return "";
        }
    }

    private String decenasin0(Integer decena) {

        switch (decena) {
            case 11:
                return "once";
            case 12:
                return "doce";
            case 13:
                return "trece";
            case 14:
                return "catorce";
            case 15:
                return "quince";
            case 16:
                return "dieciseis";
            case 17:
                return "diecisiete";
            case 18:
                return "dieciocho";
            case 19:
                return "diecinueve";
            default:
                return "";
        }
    }

    private String decena(Integer decena) {

        switch (decena) {
            case 2:
                return "veinti";
            case 3:
                return "treinta y";
            case 4:
                return "cuarenta y ";
            case 5:
                return "cincuenta y ";
            case 6:
                return "sesenta y ";
            case 7:
                return "setenta y ";
            case 8:
                return "ochenta y ";
            case 9:
                return "noventa y ";
            default:
                return "";
        }
    }

    private String centena(Integer centena) {

        switch (centena) {
            case 1:
                return "ciento ";
            case 5:
                return "quinientos ";
            case 9:
                return "novecientos ";
            default:
                return "";
        }
    }

}
