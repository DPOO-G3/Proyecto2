package consola;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Calendario extends JFrame implements ActionListener {
    private DefaultTableModel model;
    private JTable table;
    private JButton prevButton, nextButton;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<String> monthComboBox;
    private JLabel monthLabel;

    public Calendario() {
        setTitle("Calendario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        prevButton = new JButton("Anterior");
        nextButton = new JButton("Siguiente");
        yearComboBox = new JComboBox<>(getYearArray());
        monthComboBox = new JComboBox<>(getMonthArray());
        monthLabel = new JLabel();

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(prevButton, BorderLayout.WEST);
        panel.add(yearComboBox, BorderLayout.CENTER);
        panel.add(monthComboBox, BorderLayout.EAST);
        panel.add(nextButton, BorderLayout.EAST);
        panel.add(monthLabel, BorderLayout.SOUTH);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        updateCalendar();

        prevButton.addActionListener(this);
        nextButton.addActionListener(this);
        yearComboBox.addActionListener(this);
        monthComboBox.addActionListener(this);

        setVisible(true);
    }

    private Integer[] getYearArray() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Integer[] years = new Integer[currentYear - 2000 + 1];
        for (int i = 2000; i <= currentYear; i++) {
            years[i - 2000] = i;
        }
        return years;
    }

    private String[] getMonthArray() {
        String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return months;
    }

    private void updateCalendar() {
        int selectedYear = (int) yearComboBox.getSelectedItem();
        int selectedMonth = monthComboBox.getSelectedIndex();
        Calendar calendar = new GregorianCalendar(selectedYear, selectedMonth, 1);

        String[] columns = {"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};
        model.setColumnIdentifiers(columns);

        int startDay = getStartDay(selectedYear, selectedMonth);
        int numberOfDays = getNumberOfDays(selectedYear, selectedMonth);

        model.setRowCount(0);

        int day = 1;
        for (int i = 0; i < 6; i++) {
            Object[] row = new Object[7];
            for (int j = 0; j < 7; j++) {
                if ((i == 0 && j < startDay) || (day > numberOfDays)) {
                    row[j] = "";
                } else {
                    row[j] = day;
                    day++;
                }
            }
            model.addRow(row);
        }

        // Actualizar el label del mes
        monthLabel.setText(getMonthArray()[selectedMonth] + " " + selectedYear);
    }

    private int getStartDay(int year, int month) {
        Calendar calendar = new GregorianCalendar(year, month, 1);
        int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1; // Sunday is 0
        return (startDay + 7) % 7; // Adjust for starting the week on Sunday
    }

    private int getNumberOfDays(int year, int month) {
        Calendar calendar = new GregorianCalendar(year, month, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private void previousMonth() {
        int currentMonth = monthComboBox.getSelectedIndex();
        if (currentMonth == 0) {
            monthComboBox.setSelectedIndex(11);
            yearComboBox.setSelectedItem((int) yearComboBox.getSelectedItem() - 1);
        } else {
            monthComboBox.setSelectedIndex(currentMonth - 1);
        }
        updateCalendar();
    }

    private void nextMonth() {
        int currentMonth = monthComboBox.getSelectedIndex();
        if (currentMonth == 11) {
            monthComboBox.setSelectedIndex(0);
            yearComboBox.setSelectedItem((int) yearComboBox.getSelectedItem() + 1);
        } else {
            monthComboBox.setSelectedIndex(currentMonth + 1);
        }
        updateCalendar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == prevButton) {
            previousMonth();
        } else if (e.getSource() == nextButton) {
            nextMonth();
        } else if (e.getSource() == yearComboBox || e.getSource() == monthComboBox) {
            updateCalendar();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calendario());
    }
}