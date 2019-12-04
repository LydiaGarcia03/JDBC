import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Votacao implements ActionListener {

	JFrame frame = new JFrame();
	
	JPanel panelMain = new JPanel();
	JPanel panelButtons = new JPanel();
	JPanel panelResult = new JPanel();
	
	JButton btnFinalizar = new JButton("Finalizar Votação");
	
	JButton btnBasquete;
	JButton btnBike;
	JButton btnFutebol;
	JButton btnNatacao;
	JButton btnAcademia;
		
	Connection con;
	Statement statement;
	
	int countBasquete = 0, countBike = 0, countFutebol = 0, countNatacao = 0, countAcademia = 0;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Votacao();
			}
		});
	}
	
	public Votacao() {
		
		frame.setVisible(true);
		frame.setLayout(new FlowLayout());
		frame.setSize(600, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		panelButtons.setLayout(new GridLayout(1, 0));
		
		btnBasquete = new JButton(new ImageIcon(((new ImageIcon("img/basquete.png")).getImage()).getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH)));
		btnBasquete.addActionListener(this);
		btnBasquete.setActionCommand("basquete");

		btnBike = new JButton(new ImageIcon(((new ImageIcon("img/bike.png")).getImage()).getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH)));
		btnBike.addActionListener(this);
		btnBike.setActionCommand("bike");

		btnFutebol = new JButton(new ImageIcon(((new ImageIcon("img/futebol.jpg")).getImage()).getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH)));
		btnFutebol.addActionListener(this);
		btnFutebol.setActionCommand("futebol");

		btnNatacao = new JButton(new ImageIcon(((new ImageIcon("img/natacao.png")).getImage()).getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH)));
		btnNatacao.addActionListener(this);
		btnNatacao.setActionCommand("natacao");

		btnAcademia = new JButton(new ImageIcon(((new ImageIcon("img/academia.png")).getImage()).getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH)));
		btnAcademia.addActionListener(this);
		btnAcademia.setActionCommand("academia");
		
		panelButtons.add(btnBasquete);
		panelButtons.add(btnBike);
		panelButtons.add(btnFutebol);
		panelButtons.add(btnNatacao);
		panelButtons.add(btnAcademia);
		
		frame.add(panelButtons);
		
		btnFinalizar.addActionListener(this);
		panelMain.add(btnFinalizar);
		
		frame.add(panelMain);
						
		try {
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/ex05?useTimezone=true&serverTimezone=America/Sao_Paulo", "root", "root");
			
			statement = con.createStatement();
			
			String resetBasquete = "DELETE FROM basquete";
			String resetBike = "DELETE FROM bike";
			String resetFutebol = "DELETE FROM futebol";
			String resetNatacao = "DELETE FROM natacao";
			String resetAcademia = "DELETE FROM academia";
			
			statement.execute(resetBasquete);
			statement.execute(resetBike);
			statement.execute(resetFutebol);
			statement.execute(resetNatacao);
			statement.execute(resetAcademia);
			
			String sqlBasquete = "INSERT INTO basquete VALUES(0)";
			String sqlBike = "INSERT INTO bike VALUES(0)";
			String sqlFutebol = "INSERT INTO futebol VALUES(0)";
			String sqlNatacao = "INSERT INTO natacao VALUES(0)";
			String sqlAcademia = "INSERT INTO academia VALUES(0)";
			
			statement.execute(sqlBasquete);
			statement.execute(sqlBike);
			statement.execute(sqlFutebol);
			statement.execute(sqlNatacao);
			statement.execute(sqlAcademia);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
				
		switch(e.getActionCommand()) {
		
			case "basquete":
				countBasquete();
				break;
				
			case "bike":
				countBike();
				break;
				
			case "futebol":
				countFutebol();
				break;
				
			case "natacao":
				countNatacao();
				break;
				
			case "academia":
				countAcademia();
				break;
				
			case "Finalizar Votação":
				try {
					finalizar();
				} catch (SQLException e1) { e1.printStackTrace(); }
				break;
				
			default:
				
		}
		
	}
	
	public void finalizar() throws SQLException {
		
		String sqlResultBasquete = "SELECT * FROM basquete";
		String sqlResultBike = "SELECT * FROM bike";
		String sqlResultFutebol = "SELECT * FROM futebol";
		String sqlResultNatacao = "SELECT * FROM natacao";
		String sqlResultAcademia = "SELECT * FROM academia";
		
		ResultSet rsBasquete = statement.executeQuery(sqlResultBasquete);
		rsBasquete.absolute(1);
		String resultBasquete = rsBasquete.getString("votos");
		
		ResultSet rsBike = statement.executeQuery(sqlResultBike);
		rsBike.absolute(1);
		String resultBike = rsBike.getString("votos");
		
		ResultSet rsFutebol = statement.executeQuery(sqlResultFutebol);
		rsFutebol.absolute(1);
		String resultFutebol = rsFutebol.getString("votos");
		
		ResultSet rsNatacao = statement.executeQuery(sqlResultNatacao);
		rsNatacao.absolute(1);
		String resultNatacao = rsNatacao.getString("votos");
		
		ResultSet rsAcademia = statement.executeQuery(sqlResultAcademia);
		rsAcademia.absolute(1);
		String resultAcademia = rsAcademia.getString("votos");
		
		System.out.println("Resultados");
		System.out.println("");
		System.out.println("Basquete: " + resultBasquete);
		System.out.println("Bike: " + resultBike);
		System.out.println("Futebol: " + resultFutebol);
		System.out.println("Natação: " + resultNatacao);
		System.out.println("Academia: " + resultAcademia);
				
	}
	
	public void countBasquete() {
		countBasquete++;
		
		String sql = "UPDATE basquete SET votos = " + countBasquete + ";";
		try { 
			statement.execute(sql); 
		} catch (SQLException e) { e.printStackTrace(); }
//		System.out.println(sql);
	}
	
	public void countBike() {
		countBike++;
		
		String sql = "UPDATE bike SET votos = " + countBike + ";";
		try { 
			statement.execute(sql); 
		} catch (SQLException e) { e.printStackTrace(); }
//		System.out.println(sql);
	}
	
	public void countFutebol() {
		countFutebol++;
		
		String sql = "UPDATE futebol SET votos = " + countFutebol + ";";
		try { 
			statement.execute(sql); 
		} catch (SQLException e) { e.printStackTrace(); }
//		System.out.println(sql);
	}
	
	public void countNatacao() {
		countNatacao++;
		
		String sql = "UPDATE natacao SET votos = " + countNatacao + ";";
		try { 
			statement.execute(sql); 
		} catch (SQLException e) { e.printStackTrace(); }
//		System.out.println(sql);
	}

	public void countAcademia() {
		countAcademia++;
		
		String sql = "UPDATE academia SET votos = " + countAcademia + ";";
		try { 
			statement.execute(sql); 
		} catch (SQLException e) { e.printStackTrace(); }
//		System.out.println(sql);
	}
	
}
