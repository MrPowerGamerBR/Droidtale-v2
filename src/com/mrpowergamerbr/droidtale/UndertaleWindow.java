package com.mrpowergamerbr.droidtale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mrpowergamerbr.droidtale.utils.DataWrapper;
import com.mrpowergamerbr.droidtale.utils.FileUtils;
import com.mrpowergamerbr.droidtale.utils.UndertaleUtils;
import com.mrpowergamerbr.droidtale.utils.ZipUtils;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

public class UndertaleWindow {
	private JTextField textField;
	private final String uwChecksum = "28132d8c5360f6a2d72e0a0e9f171696";
	private JLabel lblstatusNoFile;
	private FileStatus fs = FileStatus.NOT_SET;
	private JButton btnBuild;
	private String fileChecksum = null;
	private File underData;
	private static final String[] magic;
	private JProgressBar progressBar;
	private StatusWindow status;

	static {
		// Super workaround because I'm lazy(tm)
		// Don't judge me im SANS *horrible joke*
		// *praises toby fox* *blames toby fox for not releasing UNDERTRALE TWO*
		// (Just kidding, I'm not Undertrash)
		String files = "assets/credits.txt assets/mus_a2.ogg assets/mus_alphysfix.ogg assets/mus_amalgam.ogg assets/mus_ambientwater.ogg assets/mus_anothermedium.ogg assets/mus_bad.ogg assets/mus_barrier.ogg assets/mus_battle1.ogg assets/mus_battle2.ogg assets/mus_bergentruckung.ogg assets/mus_bgflameA.ogg assets/mus_birdnoise.ogg assets/mus_birdsong.ogg assets/mus_boss1.ogg assets/mus_cast_1.ogg assets/mus_cast_2.ogg assets/mus_cast_3.ogg assets/mus_cast_4.ogg assets/mus_cast_5.ogg assets/mus_cast_6.ogg assets/mus_cast_7.ogg assets/mus_chokedup.ogg assets/mus_churchbell.ogg assets/mus_computer.ogg assets/mus_confession.ogg assets/mus_coolbeat.ogg assets/mus_core.ogg assets/mus_coretransition.ogg assets/mus_core_ambience.ogg assets/mus_creepy_ambience.ogg assets/mus_crickets.ogg assets/mus_cymbal.ogg assets/mus_dance_of_dog.ogg assets/mus_date.ogg assets/mus_date_fight.ogg assets/mus_date_tense.ogg assets/mus_deeploop2.ogg assets/mus_disturbing.ogg assets/mus_dogappear.ogg assets/mus_dogmeander.ogg assets/mus_dogroom.ogg assets/mus_dogsong.ogg assets/mus_dontgiveup.ogg assets/mus_doorclose.ogg assets/mus_dooropen.ogg assets/mus_drone.ogg assets/mus_dummybattle.ogg assets/mus_dununnn.ogg assets/mus_elevator.ogg assets/mus_elevator_last.ogg assets/mus_endarea_parta.ogg assets/mus_endarea_partb.ogg assets/mus_endingexcerpt1.ogg assets/mus_endingexcerpt2.ogg assets/mus_express_myself.ogg assets/mus_fallendown2.ogg assets/mus_fearsting.ogg assets/mus_flowey.ogg assets/mus_f_6s_1.ogg assets/mus_f_6s_2.ogg assets/mus_f_6s_3.ogg assets/mus_f_6s_4.ogg assets/mus_f_6s_5.ogg assets/mus_f_6s_6.ogg assets/mus_f_alarm.ogg assets/mus_f_destroyed.ogg assets/mus_f_destroyed2.ogg assets/mus_f_destroyed3.ogg assets/mus_f_finale_1.ogg assets/mus_f_finale_1_l.ogg assets/mus_f_finale_2.ogg assets/mus_f_finale_3.ogg assets/mus_f_intro.ogg assets/mus_f_newlaugh.ogg assets/mus_f_newlaugh_low.ogg assets/mus_f_part1.ogg assets/mus_f_part2.ogg assets/mus_f_part3.ogg assets/mus_f_saved.ogg assets/mus_f_wind1.ogg assets/mus_f_wind2.ogg assets/mus_gameover.ogg assets/mus_ghostbattle.ogg assets/mus_harpnoise.ogg assets/mus_hereweare.ogg assets/mus_hotel.ogg assets/mus_hotel_battle.ogg assets/mus_house1.ogg assets/mus_house2.ogg assets/mus_intronoise.ogg assets/mus_kingdescription.ogg assets/mus_lab.ogg assets/mus_leave.ogg assets/mus_menu0.ogg assets/mus_menu1.ogg assets/mus_menu2.ogg assets/mus_menu3.ogg assets/mus_menu4.ogg assets/mus_menu5.ogg assets/mus_menu6.ogg assets/mus_mettafly.ogg assets/mus_mettatonbattle.ogg assets/mus_mettaton_ex.ogg assets/mus_mettaton_neo.ogg assets/mus_mettaton_pretransform.ogg assets/mus_mettmusical1.ogg assets/mus_mettmusical2.ogg assets/mus_mettmusical3.ogg assets/mus_mettmusical4.ogg assets/mus_mettsad.ogg assets/mus_mett_applause.ogg assets/mus_mett_cheer.ogg assets/mus_mode.ogg assets/mus_mtgameshow.ogg assets/mus_muscle.ogg assets/mus_musicbox.ogg assets/mus_myemeow.ogg assets/mus_mysteriousroom2.ogg assets/mus_mystery.ogg assets/mus_napstachords.ogg assets/mus_napstahouse.ogg assets/mus_news.ogg assets/mus_news_battle.ogg assets/mus_ohyes.ogg assets/mus_oogloop.ogg assets/mus_operatile.ogg assets/mus_options_fall.ogg assets/mus_options_summer.ogg assets/mus_options_winter.ogg assets/mus_papyrus.ogg assets/mus_papyrusboss.ogg assets/mus_piano.ogg assets/mus_prebattle1.ogg assets/mus_predummy.ogg assets/mus_race.ogg assets/mus_rain.ogg assets/mus_rain_deep.ogg assets/mus_repeat_1.ogg assets/mus_repeat_2.ogg assets/mus_reunited.ogg assets/mus_rimshot.ogg assets/mus_ruins.ogg assets/mus_ruinspiano.ogg assets/mus_sansdate.ogg assets/mus_sfx_a_grab.ogg assets/mus_sfx_chainsaw.ogg assets/mus_sfx_hypergoner_charge.ogg assets/mus_sfx_hypergoner_laugh.ogg assets/mus_sfx_rainbowbeam_hold.ogg assets/mus_shop.ogg assets/mus_sigh_of_dog.ogg assets/mus_silence.ogg assets/mus_smallshock.ogg assets/mus_smile.ogg assets/mus_snoresymphony.ogg assets/mus_snowwalk.ogg assets/mus_snowy.ogg assets/mus_spider.ogg assets/mus_spoopy.ogg assets/mus_spoopy_holiday.ogg assets/mus_spoopy_wave.ogg assets/mus_star.ogg assets/mus_sticksnap.ogg assets/mus_story.ogg assets/mus_story_stuck.ogg assets/mus_st_happytown.ogg assets/mus_st_him.ogg assets/mus_st_meatfactory.ogg assets/mus_st_troubledingle.ogg assets/mus_temshop.ogg assets/mus_temvillage.ogg assets/mus_tension.ogg assets/mus_tone2.ogg assets/mus_tone3.ogg assets/mus_toomuch.ogg assets/mus_toriel.ogg assets/mus_town.ogg assets/mus_tv.ogg assets/mus_undyneboss.ogg assets/mus_undynefast.ogg assets/mus_undynepiano.ogg assets/mus_undynescary.ogg assets/mus_undynetheme.ogg assets/mus_undynetruetheme.ogg assets/mus_vsasgore.ogg assets/mus_waterfall.ogg assets/mus_waterquiet.ogg assets/mus_wawa.ogg assets/mus_whoopee.ogg assets/mus_wind.ogg assets/mus_woofenstein.ogg assets/mus_woofenstein_loop.ogg assets/mus_wrongnumbersong.ogg assets/mus_wrongworld.ogg assets/mus_xpart.ogg assets/mus_xpart_2.ogg assets/mus_xpart_a.ogg assets/mus_xpart_b.ogg assets/mus_xpart_back.ogg assets/mus_x_undyne.ogg assets/mus_x_undyne_pre.ogg assets/mus_yourbestfriend_3.ogg assets/mus_zzz_c.ogg assets/mus_zzz_c2.ogg assets/mus_zz_megalovania.ogg assets/mus_z_ending.ogg assets/snd_ballchime.ogg assets/snd_bombfall.ogg assets/snd_bombsplosion.ogg assets/snd_buzzing.ogg assets/snd_curtgunshot.ogg assets/snd_fall2.ogg assets/snd_flameloop.ogg assets/snd_heavydamage.ogg assets/snd_mushroomdance.ogg";
		files = files.replace("assets/", "");
		magic = files.split(" ");
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Droidtale Automagical Builder");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("com/mrpowergamerbr/droidtale/icon.png")));
		
		JLabel lblUndertaleDatawinFile = new JLabel("<html><b>data.win:</b></html>");
		lblUndertaleDatawinFile.setBounds(10, 11, 135, 14);
		frame.getContentPane().add(lblUndertaleDatawinFile);

		textField = new JTextField();
		textField.setBounds(10, 25, 121, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				File file = new File(textField.getText());
				
				DataWrapper dw = UndertaleUtils.checkData(file);
				handleDataWrapper(dw);
			}
			public void removeUpdate(DocumentEvent e) {
				File file = new File(textField.getText());
				
				DataWrapper dw = UndertaleUtils.checkData(file);
				handleDataWrapper(dw);
			}
			public void insertUpdate(DocumentEvent e) {
				File file = new File(textField.getText());
				
				DataWrapper dw = UndertaleUtils.checkData(file);
				handleDataWrapper(dw);
			}
		});

		JButton btnNewButton = new JButton("Select");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					
					textField.setText(file.getPath());
				}
			}
		});
		
		btnNewButton.setBounds(141, 25, 67, 20);
		frame.getContentPane().add(btnNewButton);

		lblstatusNoFile = new JLabel("<html><b>Status:</b> <font color='red'>No file selected</font>.");
		lblstatusNoFile.setBounds(10, 56, 198, 55);
		lblstatusNoFile.setVerticalAlignment(JLabel.TOP);
		frame.getContentPane().add(lblstatusNoFile);

		btnBuild = new JButton("Start!");
		btnBuild.setEnabled(false);
		btnBuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fs.go()) {
					Thread t = new Thread() {
						public void run() {
							try {
								status = new StatusWindow();
								status.createAndShowGUI();
								log("Starting to build Droidtale...");
								log("Only use this if you are using Undertale v1.001!");
								log("For reference, the v1.001 checksum is " + UndertaleUtils.ver1001Checksum);					
								log("Selected file checksum: " + fileChecksum);
								log("\nChecking if the UndertaleWrapper.apk wasn't tampered");
								InputStream is = getClass().getClassLoader().getResourceAsStream("com/mrpowergamerbr/droidtale/UndertaleWrapper.apk");
								String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(is);
								is.close();
								log("Original file md5: " + uwChecksum);
								log("UndertaleWrapper @ " + new File(UndertaleWindow.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName() + ": " + md5);
								if (!uwChecksum.equals(md5)) {
									log("!!! INVALID UNDERTALEWRAPPER CHECKSUM !!!");
									return;
								}
								log("\nCopying UndertaleWrapper.apk...");


								File file = new File("UndertaleWrapper.apk");
								if (file.exists()) {
									file.delete();
								}
								if (!file.exists()) {
									InputStream link = (getClass().getClassLoader().getResourceAsStream("com/mrpowergamerbr/droidtale/UndertaleWrapper.apk"));
									Files.copy(link, file.getAbsoluteFile().toPath());
								}
								progressBar.setValue(5);
								File data = new File("data.win");
								data.delete();
								log("Copying data.win...");
								{
									long bytes = Files.copy(new FileInputStream(underData), data.getAbsoluteFile().toPath());
									log("Copy finished! " + bytes + " bytes transferred!");
								}
								progressBar.setValue(10);
								log("Renaming data.win -> game.droid...");
								data.renameTo(new File("game.droid"));
								progressBar.setValue(12);
								log("Rename finished!");
								log("Moving game.droid to UndertaleWrapper.apk...");
								ZipUtils.addFilesToExistingZip(file, new File[] { new File("game.droid") });
								progressBar.setValue(20);
								log("Success!");

								log("\nCopying aapt.exe...");
								File aapt = new File("aapt.exe");
								aapt.delete();
								{
									InputStream link = (getClass().getClassLoader().getResourceAsStream("com/mrpowergamerbr/droidtale/aapt.exe"));
									long bytes = Files.copy(link, aapt.getAbsoluteFile().toPath());
									log("Copy finished! " + bytes + " bytes transferred!");
								}
								progressBar.setValue(25);

								log("Copying assets files...");

								for (String asset : magic) {
									File fAsset = new File(underData.getParent() + "/" + asset);
									File nAsset = new File("assets/" + asset);
									nAsset.mkdirs();
									nAsset.delete();
									Files.copy(new FileInputStream(fAsset), nAsset.getAbsoluteFile().toPath());
								}
								progressBar.setValue(50);

								log("Adding assets files via aapt.exe...");
								// aapt add -f -v UndertaleWrapper.apk assets/credits.txt assets/mus_a2.ogg assets/mus_alphysfix.ogg assets/mus_amalgam.ogg assets/mus_ambientwater.ogg assets/mus_anothermedium.ogg assets/mus_bad.ogg assets/mus_barrier.ogg assets/mus_battle1.ogg assets/mus_battle2.ogg assets/mus_bergentruckung.ogg assets/mus_bgflameA.ogg assets/mus_birdnoise.ogg assets/mus_birdsong.ogg assets/mus_boss1.ogg assets/mus_cast_1.ogg assets/mus_cast_2.ogg assets/mus_cast_3.ogg assets/mus_cast_4.ogg assets/mus_cast_5.ogg assets/mus_cast_6.ogg assets/mus_cast_7.ogg assets/mus_chokedup.ogg assets/mus_churchbell.ogg assets/mus_computer.ogg assets/mus_confession.ogg assets/mus_coolbeat.ogg assets/mus_core.ogg assets/mus_coretransition.ogg assets/mus_core_ambience.ogg assets/mus_creepy_ambience.ogg assets/mus_crickets.ogg assets/mus_cymbal.ogg assets/mus_dance_of_dog.ogg assets/mus_date.ogg assets/mus_date_fight.ogg assets/mus_date_tense.ogg assets/mus_deeploop2.ogg assets/mus_disturbing.ogg assets/mus_dogappear.ogg assets/mus_dogmeander.ogg assets/mus_dogroom.ogg assets/mus_dogsong.ogg assets/mus_dontgiveup.ogg assets/mus_doorclose.ogg assets/mus_dooropen.ogg assets/mus_drone.ogg assets/mus_dummybattle.ogg assets/mus_dununnn.ogg assets/mus_elevator.ogg assets/mus_elevator_last.ogg assets/mus_endarea_parta.ogg assets/mus_endarea_partb.ogg assets/mus_endingexcerpt1.ogg assets/mus_endingexcerpt2.ogg assets/mus_express_myself.ogg assets/mus_fallendown2.ogg assets/mus_fearsting.ogg assets/mus_flowey.ogg assets/mus_f_6s_1.ogg assets/mus_f_6s_2.ogg assets/mus_f_6s_3.ogg assets/mus_f_6s_4.ogg assets/mus_f_6s_5.ogg assets/mus_f_6s_6.ogg assets/mus_f_alarm.ogg assets/mus_f_destroyed.ogg assets/mus_f_destroyed2.ogg assets/mus_f_destroyed3.ogg assets/mus_f_finale_1.ogg assets/mus_f_finale_1_l.ogg assets/mus_f_finale_2.ogg assets/mus_f_finale_3.ogg assets/mus_f_intro.ogg assets/mus_f_newlaugh.ogg assets/mus_f_newlaugh_low.ogg assets/mus_f_part1.ogg assets/mus_f_part2.ogg assets/mus_f_part3.ogg assets/mus_f_saved.ogg assets/mus_f_wind1.ogg assets/mus_f_wind2.ogg assets/mus_gameover.ogg assets/mus_ghostbattle.ogg assets/mus_harpnoise.ogg assets/mus_hereweare.ogg assets/mus_hotel.ogg assets/mus_hotel_battle.ogg assets/mus_house1.ogg assets/mus_house2.ogg assets/mus_intronoise.ogg assets/mus_kingdescription.ogg assets/mus_lab.ogg assets/mus_leave.ogg assets/mus_menu0.ogg assets/mus_menu1.ogg assets/mus_menu2.ogg assets/mus_menu3.ogg assets/mus_menu4.ogg assets/mus_menu5.ogg assets/mus_menu6.ogg assets/mus_mettafly.ogg assets/mus_mettatonbattle.ogg assets/mus_mettaton_ex.ogg assets/mus_mettaton_neo.ogg assets/mus_mettaton_pretransform.ogg assets/mus_mettmusical1.ogg assets/mus_mettmusical2.ogg assets/mus_mettmusical3.ogg assets/mus_mettmusical4.ogg assets/mus_mettsad.ogg assets/mus_mett_applause.ogg assets/mus_mett_cheer.ogg assets/mus_mode.ogg assets/mus_mtgameshow.ogg assets/mus_muscle.ogg assets/mus_musicbox.ogg assets/mus_myemeow.ogg assets/mus_mysteriousroom2.ogg assets/mus_mystery.ogg assets/mus_napstachords.ogg assets/mus_napstahouse.ogg assets/mus_news.ogg assets/mus_news_battle.ogg assets/mus_ohyes.ogg assets/mus_oogloop.ogg assets/mus_operatile.ogg assets/mus_options_fall.ogg assets/mus_options_summer.ogg assets/mus_options_winter.ogg assets/mus_papyrus.ogg assets/mus_papyrusboss.ogg assets/mus_piano.ogg assets/mus_prebattle1.ogg assets/mus_predummy.ogg assets/mus_race.ogg assets/mus_rain.ogg assets/mus_rain_deep.ogg assets/mus_repeat_1.ogg assets/mus_repeat_2.ogg assets/mus_reunited.ogg assets/mus_rimshot.ogg assets/mus_ruins.ogg assets/mus_ruinspiano.ogg assets/mus_sansdate.ogg assets/mus_sfx_a_grab.ogg assets/mus_sfx_chainsaw.ogg assets/mus_sfx_hypergoner_charge.ogg assets/mus_sfx_hypergoner_laugh.ogg assets/mus_sfx_rainbowbeam_hold.ogg assets/mus_shop.ogg assets/mus_sigh_of_dog.ogg assets/mus_silence.ogg assets/mus_smallshock.ogg assets/mus_smile.ogg assets/mus_snoresymphony.ogg assets/mus_snowwalk.ogg assets/mus_snowy.ogg assets/mus_spider.ogg assets/mus_spoopy.ogg assets/mus_spoopy_holiday.ogg assets/mus_spoopy_wave.ogg assets/mus_star.ogg assets/mus_sticksnap.ogg assets/mus_story.ogg assets/mus_story_stuck.ogg assets/mus_st_happytown.ogg assets/mus_st_him.ogg assets/mus_st_meatfactory.ogg assets/mus_st_troubledingle.ogg assets/mus_temshop.ogg assets/mus_temvillage.ogg assets/mus_tension.ogg assets/mus_tone2.ogg assets/mus_tone3.ogg assets/mus_toomuch.ogg assets/mus_toriel.ogg assets/mus_town.ogg assets/mus_tv.ogg assets/mus_undyneboss.ogg assets/mus_undynefast.ogg assets/mus_undynepiano.ogg assets/mus_undynescary.ogg assets/mus_undynetheme.ogg assets/mus_undynetruetheme.ogg assets/mus_vsasgore.ogg assets/mus_waterfall.ogg assets/mus_waterquiet.ogg assets/mus_wawa.ogg assets/mus_whoopee.ogg assets/mus_wind.ogg assets/mus_woofenstein.ogg assets/mus_woofenstein_loop.ogg assets/mus_wrongnumbersong.ogg assets/mus_wrongworld.ogg assets/mus_xpart.ogg assets/mus_xpart_2.ogg assets/mus_xpart_a.ogg assets/mus_xpart_b.ogg assets/mus_xpart_back.ogg assets/mus_x_undyne.ogg assets/mus_x_undyne_pre.ogg assets/mus_yourbestfriend_3.ogg assets/mus_zzz_c.ogg assets/mus_zzz_c2.ogg assets/mus_zz_megalovania.ogg assets/mus_z_ending.ogg assets/snd_ballchime.ogg assets/snd_bombfall.ogg assets/snd_bombsplosion.ogg assets/snd_buzzing.ogg assets/snd_curtgunshot.ogg assets/snd_fall2.ogg assets/snd_flameloop.ogg assets/snd_heavydamage.ogg assets/snd_mushroomdance.ogg
								Runtime rt = Runtime.getRuntime();
								final Process pr = rt.exec("aapt add -f -v UndertaleWrapper.apk assets/credits.txt assets/mus_a2.ogg assets/mus_alphysfix.ogg assets/mus_amalgam.ogg assets/mus_ambientwater.ogg assets/mus_anothermedium.ogg assets/mus_bad.ogg assets/mus_barrier.ogg assets/mus_battle1.ogg assets/mus_battle2.ogg assets/mus_bergentruckung.ogg assets/mus_bgflameA.ogg assets/mus_birdnoise.ogg assets/mus_birdsong.ogg assets/mus_boss1.ogg assets/mus_cast_1.ogg assets/mus_cast_2.ogg assets/mus_cast_3.ogg assets/mus_cast_4.ogg assets/mus_cast_5.ogg assets/mus_cast_6.ogg assets/mus_cast_7.ogg assets/mus_chokedup.ogg assets/mus_churchbell.ogg assets/mus_computer.ogg assets/mus_confession.ogg assets/mus_coolbeat.ogg assets/mus_core.ogg assets/mus_coretransition.ogg assets/mus_core_ambience.ogg assets/mus_creepy_ambience.ogg assets/mus_crickets.ogg assets/mus_cymbal.ogg assets/mus_dance_of_dog.ogg assets/mus_date.ogg assets/mus_date_fight.ogg assets/mus_date_tense.ogg assets/mus_deeploop2.ogg assets/mus_disturbing.ogg assets/mus_dogappear.ogg assets/mus_dogmeander.ogg assets/mus_dogroom.ogg assets/mus_dogsong.ogg assets/mus_dontgiveup.ogg assets/mus_doorclose.ogg assets/mus_dooropen.ogg assets/mus_drone.ogg assets/mus_dummybattle.ogg assets/mus_dununnn.ogg assets/mus_elevator.ogg assets/mus_elevator_last.ogg assets/mus_endarea_parta.ogg assets/mus_endarea_partb.ogg assets/mus_endingexcerpt1.ogg assets/mus_endingexcerpt2.ogg assets/mus_express_myself.ogg assets/mus_fallendown2.ogg assets/mus_fearsting.ogg assets/mus_flowey.ogg assets/mus_f_6s_1.ogg assets/mus_f_6s_2.ogg assets/mus_f_6s_3.ogg assets/mus_f_6s_4.ogg assets/mus_f_6s_5.ogg assets/mus_f_6s_6.ogg assets/mus_f_alarm.ogg assets/mus_f_destroyed.ogg assets/mus_f_destroyed2.ogg assets/mus_f_destroyed3.ogg assets/mus_f_finale_1.ogg assets/mus_f_finale_1_l.ogg assets/mus_f_finale_2.ogg assets/mus_f_finale_3.ogg assets/mus_f_intro.ogg assets/mus_f_newlaugh.ogg assets/mus_f_newlaugh_low.ogg assets/mus_f_part1.ogg assets/mus_f_part2.ogg assets/mus_f_part3.ogg assets/mus_f_saved.ogg assets/mus_f_wind1.ogg assets/mus_f_wind2.ogg assets/mus_gameover.ogg assets/mus_ghostbattle.ogg assets/mus_harpnoise.ogg assets/mus_hereweare.ogg assets/mus_hotel.ogg assets/mus_hotel_battle.ogg assets/mus_house1.ogg assets/mus_house2.ogg assets/mus_intronoise.ogg assets/mus_kingdescription.ogg assets/mus_lab.ogg assets/mus_leave.ogg assets/mus_menu0.ogg assets/mus_menu1.ogg assets/mus_menu2.ogg assets/mus_menu3.ogg assets/mus_menu4.ogg assets/mus_menu5.ogg assets/mus_menu6.ogg assets/mus_mettafly.ogg assets/mus_mettatonbattle.ogg assets/mus_mettaton_ex.ogg assets/mus_mettaton_neo.ogg assets/mus_mettaton_pretransform.ogg assets/mus_mettmusical1.ogg assets/mus_mettmusical2.ogg assets/mus_mettmusical3.ogg assets/mus_mettmusical4.ogg assets/mus_mettsad.ogg assets/mus_mett_applause.ogg assets/mus_mett_cheer.ogg assets/mus_mode.ogg assets/mus_mtgameshow.ogg assets/mus_muscle.ogg assets/mus_musicbox.ogg assets/mus_myemeow.ogg assets/mus_mysteriousroom2.ogg assets/mus_mystery.ogg assets/mus_napstachords.ogg assets/mus_napstahouse.ogg assets/mus_news.ogg assets/mus_news_battle.ogg assets/mus_ohyes.ogg assets/mus_oogloop.ogg assets/mus_operatile.ogg assets/mus_options_fall.ogg assets/mus_options_summer.ogg assets/mus_options_winter.ogg assets/mus_papyrus.ogg assets/mus_papyrusboss.ogg assets/mus_piano.ogg assets/mus_prebattle1.ogg assets/mus_predummy.ogg assets/mus_race.ogg assets/mus_rain.ogg assets/mus_rain_deep.ogg assets/mus_repeat_1.ogg assets/mus_repeat_2.ogg assets/mus_reunited.ogg assets/mus_rimshot.ogg assets/mus_ruins.ogg assets/mus_ruinspiano.ogg assets/mus_sansdate.ogg assets/mus_sfx_a_grab.ogg assets/mus_sfx_chainsaw.ogg assets/mus_sfx_hypergoner_charge.ogg assets/mus_sfx_hypergoner_laugh.ogg assets/mus_sfx_rainbowbeam_hold.ogg assets/mus_shop.ogg assets/mus_sigh_of_dog.ogg assets/mus_silence.ogg assets/mus_smallshock.ogg assets/mus_smile.ogg assets/mus_snoresymphony.ogg assets/mus_snowwalk.ogg assets/mus_snowy.ogg assets/mus_spider.ogg assets/mus_spoopy.ogg assets/mus_spoopy_holiday.ogg assets/mus_spoopy_wave.ogg assets/mus_star.ogg assets/mus_sticksnap.ogg assets/mus_story.ogg assets/mus_story_stuck.ogg assets/mus_st_happytown.ogg assets/mus_st_him.ogg assets/mus_st_meatfactory.ogg assets/mus_st_troubledingle.ogg assets/mus_temshop.ogg assets/mus_temvillage.ogg assets/mus_tension.ogg assets/mus_tone2.ogg assets/mus_tone3.ogg assets/mus_toomuch.ogg assets/mus_toriel.ogg assets/mus_town.ogg assets/mus_tv.ogg assets/mus_undyneboss.ogg assets/mus_undynefast.ogg assets/mus_undynepiano.ogg assets/mus_undynescary.ogg assets/mus_undynetheme.ogg assets/mus_undynetruetheme.ogg assets/mus_vsasgore.ogg assets/mus_waterfall.ogg assets/mus_waterquiet.ogg assets/mus_wawa.ogg assets/mus_whoopee.ogg assets/mus_wind.ogg assets/mus_woofenstein.ogg assets/mus_woofenstein_loop.ogg assets/mus_wrongnumbersong.ogg assets/mus_wrongworld.ogg assets/mus_xpart.ogg assets/mus_xpart_2.ogg assets/mus_xpart_a.ogg assets/mus_xpart_b.ogg assets/mus_xpart_back.ogg assets/mus_x_undyne.ogg assets/mus_x_undyne_pre.ogg assets/mus_yourbestfriend_3.ogg assets/mus_zzz_c.ogg assets/mus_zzz_c2.ogg assets/mus_zz_megalovania.ogg assets/mus_z_ending.ogg assets/snd_ballchime.ogg assets/snd_bombfall.ogg assets/snd_bombsplosion.ogg assets/snd_buzzing.ogg assets/snd_curtgunshot.ogg assets/snd_fall2.ogg assets/snd_flameloop.ogg assets/snd_heavydamage.ogg assets/snd_mushroomdance.ogg");

								new Thread(new Runnable() {
									public void run() {
										BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
										String line = null; 

										try {
											while ((line = input.readLine()) != null)
												System.out.println(line);
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
								}).start();

								pr.waitFor();

								progressBar.setValue(80);
								log("Success!");
								log("Signing the APK...");
								Sign.sign(file.toPath().toString(), true);

								log("Success!");
								log("\nCleaning up...");

								progressBar.setValue(90);

								aapt.delete();
								FileUtils.deleteFile(new File("assets/"));
								new File("game.droid").delete();

								progressBar.setValue(100);

								JOptionPane.showMessageDialog(null, "Finished building the APK!\n\nNow, copy the UndertaleWrapper.apk to your device and install it like any other APK file.", "Done!", JOptionPane.INFORMATION_MESSAGE);

							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					};
					t.start();

				} else {
					// Do nothing
					// Why this else even exists then?
					// Just to consume space of course!
					// INSERT SANS JOKE HERE
					// OMG SANS
				}
			}
		});
		btnBuild.setBounds(67, 122, 89, 23);
		frame.getContentPane().add(btnBuild);

		progressBar = new JProgressBar();
		progressBar.setBounds(10, 156, 198, 14);
		frame.getContentPane().add(progressBar);

		//Display the window.
		frame.setSize(234, 216);
		frame.setVisible(true);
	}

	public void log(final String str) {
		System.out.println(str);
		status.textPane.setText((!status.textPane.getText().isEmpty() ? status.textPane.getText() + "\n" : "") + str);
		status.textPane.setCaretPosition(status.textPane.getText().length());
	}

	public void handleDataWrapper(DataWrapper dw) {
		setFileStatus(dw.fs);

		fileChecksum = dw.md5;
		underData = dw.data;
	}
	
	public void setFileStatus(FileStatus fs) {
		this.fs = fs;
		if (fs == FileStatus.MISSING_ASSETS) {
			lblstatusNoFile.setText("<html><b>Status:</b> <font color='red'>Missing game assets</font>!");
			btnBuild.setEnabled(false);
		} else if (fs == FileStatus.VALID) {
			lblstatusNoFile.setText("<html><b>Status:</b> <font color='green'>Checksum ok</font>!");
			btnBuild.setEnabled(true);
		} else if (fs == FileStatus.INVALID_CHECKSUM) {
			lblstatusNoFile.setText("<html><b>Status:</b> <font color='orange'>Invalid checksum</font>!<br>Are you using mods?<br>If yes, then continue!");
			btnBuild.setEnabled(true);
		} else {
			lblstatusNoFile.setText("<html><b>Status:</b> <font color='red'>Something went wrong</font>!");
			btnBuild.setEnabled(false);
		}
	}
}
