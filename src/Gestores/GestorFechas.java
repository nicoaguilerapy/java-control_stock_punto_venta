package Gestores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class GestorFechas {

    private Date fecha;
    private SimpleDateFormat dateFormat, hourFormat;
    private String resultado;
    private long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

    public GestorFechas() {
    }

    public String getHoraActualPuntos() {
        hourFormat = new SimpleDateFormat("HH:mm:ss");
        resultado = hourFormat.format(new Date());
        return resultado;
    }

    public String getHoraActual() {
        hourFormat = new SimpleDateFormat("HH:mm:ss");
        resultado = hourFormat.format(new Date());

        String[] horaPartes = resultado.split(":");
        String hora = horaPartes[0];
        String minuto = horaPartes[1];
        String segundo = horaPartes[2];
        resultado = hora + "" + minuto + "" + segundo;

        return resultado;
    }

    public String getHoraFecha() {
        resultado = getFechaActual() + getHoraActual();
        return resultado;
    }

    public boolean compareDates(String d1, String d2) {
        Date date1 = null, date2 = null;
        try {
            date1 = new SimpleDateFormat("yyyy/MM/dd").parse(d1);
            date2 = new SimpleDateFormat("yyyy/MM/dd").parse(d2);
        } catch (ParseException e) {
            System.out.println("herramientas.GestorFechas.compareDates() error de fechas: " + d1 + " | " + d2);
        }

        if (date1.compareTo(date2) < 0) {
            return true;
        }

        return false;
    }

    public String invertir(String fecha) {

        String[] fechasPartes = fecha.split("/");
        String c = fechasPartes[0];
        String b = fechasPartes[1];
        String a = fechasPartes[2];

        fecha = a + "/" + b + "/" + c;

        return fecha;
    }

    public String getFechaActual() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        resultado = dateFormat.format(new Date());
        String[] fechasPartesNac = resultado.split("/");
        String dayTmpNac = fechasPartesNac[0];
        String mouthTmpNac = fechasPartesNac[1];
        String yearTmpNac = fechasPartesNac[2];
        resultado = dayTmpNac + "" + mouthTmpNac + "" + yearTmpNac;
        return resultado;

    }

    public String getAnoActual() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        resultado = dateFormat.format(new Date());
        String[] fechasPartesNac = resultado.split("/");
        String dayTmpNac = fechasPartesNac[0];
        String mouthTmpNac = fechasPartesNac[1];
        String yearTmpNac = fechasPartesNac[2];
        resultado = dayTmpNac + "" + mouthTmpNac + "" + yearTmpNac;
        return yearTmpNac;
    }

    public String getAno(String fecha) {
        String[] fechasPartesNac = fecha.split("/");
        String dayTmpNac = fechasPartesNac[0];
        String mouthTmpNac = fechasPartesNac[1];
        String yearTmpNac = fechasPartesNac[2];
        resultado = dayTmpNac + "" + mouthTmpNac + "" + yearTmpNac;
        return dayTmpNac;
    }

    public String getMes(String fecha) {
        String[] fechasPartesNac = fecha.split("/");
        String dayTmpNac = fechasPartesNac[0];
        String mouthTmpNac = fechasPartesNac[1];
        String yearTmpNac = fechasPartesNac[2];
        resultado = dayTmpNac + "" + mouthTmpNac + "" + yearTmpNac;
        return mouthTmpNac;
    }

    public String getMesctual() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        resultado = dateFormat.format(new Date());
        String[] fechasPartesNac = resultado.split("/");
        String dayTmpNac = fechasPartesNac[0];
        String mouthTmpNac = fechasPartesNac[1];
        String yearTmpNac = fechasPartesNac[2];
        resultado = dayTmpNac + "" + mouthTmpNac + "" + yearTmpNac;
        return mouthTmpNac;
    }

    public String getFechaActualBarras() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        resultado = dateFormat.format(new Date());
        return resultado;
    }

    public String getMesString(Integer codMes) {
        switch (codMes) {
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";

            default:
                return "Vacio";
        }
    }

    public String getDia() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cini = Calendar.getInstance();

        int codDia = cini.get(Calendar.DAY_OF_WEEK);
        switch (codDia) {
            case 0:
                return "Domingo";
            case 1:
                return "Lunes";
            case 2:
                return "Martes";
            case 3:
                return "Miercoles";
            case 4:
                return "Jueves";
            case 5:
                return "Viernes";
            case 6:
                return "Sabado";

            default:
                return "error";
        }
    }

    public String getMesSig(String fecha) {
        int x, sw = 0;
        String fechaR;

        String[] fechasPartes = fecha.split("/");
        String day = fechasPartes[0];
        String mouth = fechasPartes[1];
        String year = fechasPartes[2];

        Integer d = Integer.parseInt(day);
        Integer m = Integer.parseInt(mouth);
        Integer y = Integer.parseInt(year);

        if (m < 12) {
            m++;
            while (sw == 0) {

                if (d < 10) {
                    day = "0" + d;
                } else {
                    day = "" + d;
                }
                if (m < 10) {
                    mouth = "0" + m;
                } else {
                    mouth = "" + m;
                }

                fechaR = day + "/" + mouth + "/" + y;

                if (validarFecha(resultado)) {
                    sw = 1;
                } else {
                    d--;
                    if (m < 10) {
                        resultado = d + "/0" + m + "/" + y;
                    } else {
                        resultado = d + "/" + m + "/" + y;
                    }
                }

            }
        } else {
            m = 1;
            y++;
            resultado = d + "/" + m + "/" + y;
        }

        return resultado;

    }

    public String getDiaSig(String fecha) {
        ArrayList Lista = new ArrayList();
        String[] fechasPartes = fecha.split("/");
        String day = fechasPartes[0];
        String mouth = fechasPartes[1];
        String year = fechasPartes[2];

        String resultado = "";
        int x = -1;
        String fechaR;

        Integer d = Integer.parseInt(day);
        Integer m = Integer.parseInt(mouth);
        Integer y = Integer.parseInt(year);

        d = d + 1;
        if (d < 10) {
            day = "0" + d;
        } else {
            day = "" + d;
        }
        if (m < 10) {
            mouth = "0" + m;
        } else {
            mouth = "" + m;
        }

        fechaR = day + "/" + mouth + "/" + y;

        if (validarFecha(resultado)) {
            return fechaR;
        } else {
            m++;
            d = 1;
            if (d < 10) {
                day = "0" + d;
            } else {
                day = "" + d;
            }
            if (m < 10) {
                mouth = "0" + m;
            } else {
                mouth = "" + m;
            }

            fechaR = day + "/" + mouth + "/" + y;

            if (validarFecha(fechaR)) {
                return fechaR;
            }
        }
        return null;
    }

    public boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public ArrayList yearArrayList(String f1, String f2) {
        ArrayList<String> fechaArray = new ArrayList();

        Integer fecha1 = Integer.parseInt(getAno(f1));
        Integer fecha2 = Integer.parseInt(getAno(f2));

        if (fecha1 == fecha2) {
            fechaArray.add(fecha1.toString());
            System.out.println(fecha2);
            return fechaArray;
        }
        int sw = 0;
        while (sw == 0) {
            fechaArray.add(fecha2.toString());
            if (fecha2 - fecha1 == 0) {
                sw = 1;
            } else {
                fecha2--;
            }
        }
        return fechaArray;
    }

    public String getPrevDay(String fecha, Integer d) {
        Date date1 = null, date2 = null;
        try {
            date1 = new SimpleDateFormat("yyyy/MM/dd").parse(fecha);
        } catch (Exception e) {
            return "";
        }

        dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        date2 = new Date(date1.getTime() - (MILLIS_IN_A_DAY * d));
        return dateFormat.format(date2);
    }

}
