package fun.gbr.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import fun.gbr.parameters.Options;
import fun.gbr.service.ListService;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SortingHatGUI {

	private JFrame frmSortinghat;
	private JTextField tfList;
	private JTextField tfError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SortingHatGUI window = new SortingHatGUI();
					window.frmSortinghat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SortingHatGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSortinghat = new JFrame();
		frmSortinghat.setIconImage(Toolkit.getDefaultToolkit().getImage(SortingHatGUI.class.getResource("/fun/gbr/gui/top_hat_1.png")));
		frmSortinghat.setTitle("SortingHat");
		frmSortinghat.setBounds(100, 100, 450, 300);
		frmSortinghat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSortinghat.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblList = new JLabel("List");
		frmSortinghat.getContentPane().add(lblList, "4, 4");
		
		tfList = new JTextField();
		tfList.setText("item 1, item 2, item 3, ...");
		frmSortinghat.getContentPane().add(tfList, "8, 4, fill, default");
		tfList.setColumns(10);
		
		JButton btnShuffle = new JButton("Shuffle");
		btnShuffle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfError.setText("");
				try {
					tfList.setText(ListService.shuffle(tfList.getText()));
					tfError.setText("");
				} catch (IllegalArgumentException iae) {
					tfError.setText("Impossible to shuffle this list while respecting Max Repeats");
				}
			}
		});
		frmSortinghat.getContentPane().add(btnShuffle, "8, 6");
		
		JLabel lblMaxRepeats = new JLabel("Max Repeats");
		frmSortinghat.getContentPane().add(lblMaxRepeats, "4, 10");
		
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(Options.getMaxRepeats(), 0, Integer.MAX_VALUE, 1));
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Options.setMaxRepeats((Integer)spinner.getValue());
			}
		});
		frmSortinghat.getContentPane().add(spinner, "8, 10, left, default");
		
		tfError = new JTextField();
		tfError.setEditable(false);
		tfError.setForeground(Color.RED);
		tfError.setBorder(BorderFactory.createEmptyBorder());
		frmSortinghat.getContentPane().add(tfError, "4, 12, 5, 1, fill, default");
		tfError.setColumns(10);
	}

}
