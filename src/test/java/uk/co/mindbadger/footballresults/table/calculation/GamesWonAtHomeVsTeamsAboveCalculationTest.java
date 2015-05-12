package uk.co.mindbadger.footballresults.table.calculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresults.table.Table;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresults.table.TableRowAfterResult;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GamesWonAtHomeVsTeamsAboveCalculationTest {
	private GamesWonAtHomeVsTeamsAboveCalculation objectUnderTest;
	
	@Mock
	private TableRowAfterResult<String,String,String> mockPreviousTableRow;

	@Mock
	private Fixture<String> mockFixture;
	
	@Mock
	private Team<String> mockTeamForCalculation;

	@Mock
	private TeamFixtureContext mockFixtureTeamContext;
	
	@Mock
	private TeamFixtureContext mockOppositionTeamContext;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	/*
	 * SCENARIOS
	 * 
	 * First Game of Season (no above or below)
	 * --------------------
	 * Home Win
	 * Home Defeat
	 * Home Draw
	 * Away Win
	 * Away Defeat
	 * Away Draw
	 * 
	 * After First Game of Season
	 * --------------------------
	 * Home Win vs Team Above
	 * Home Win vs Team Below
	 * Home Defeat vs Team Above
	 * Home Defeat vs Team Below
	 * Home Draw vs Team Above
	 * Home Draw vs Team Below
	 * Away Win vs Team Above
	 * Away Win vs Team Below
	 * Away Defeat vs Team Above
	 * Away Defeat vs Team Below
	 * Away Draw vs Team Above
	 * Away Draw vs Team Below
	 */
	
	@Test
	public void shouldReturnAnIncrementWhenThereIsAPreviousRowForAHomeWinAgainstATeamAbove () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (4, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeWinAgainstATeamBelow () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
		
		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

//	@Test
//	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeWinAgainstATeamBelow () {
//		// Given
//		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
//		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");
//
//		when (mockFixture.getHomeTeam()).thenReturn(mockTeamForCalculation);
//		when (mockFixture.getHomeGoals()).thenReturn(3);
//		when (mockFixture.getAwayTeam()).thenReturn(mockOpposingTeam);
//		when (mockFixture.getAwayGoals()).thenReturn(1);
//		
//		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
//		when (mockPreviousTableRow.getParentTable()).thenReturn(mockParentTable);
//		
//		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRow);
//		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
//		
//		when (mockPreviousTableRow.getLeaguePosition()).thenReturn(3);
//		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(10);
//		
//		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRow, mockFixture);
//		
//		// When
//		int result = objectUnderTest.calculate();
//		
//		// Then
//		assertEquals (3, result);
//	}
//
//	@Test
//	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDrawAgainstATeamAbove () {
//		// Given
//		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
//		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");
//
//		when (mockFixture.getHomeTeam()).thenReturn(mockTeamForCalculation);
//		when (mockFixture.getHomeGoals()).thenReturn(3);
//		when (mockFixture.getAwayTeam()).thenReturn(mockOpposingTeam);
//		when (mockFixture.getAwayGoals()).thenReturn(3);
//		
//		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
//		when (mockPreviousTableRow.getParentTable()).thenReturn(mockParentTable);
//		
//		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRow);
//		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
//		
//		when (mockPreviousTableRow.getLeaguePosition()).thenReturn(10);
//		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(3);
//		
//		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRow, mockFixture);
//		
//		// When
//		int result = objectUnderTest.calculate();
//		
//		// Then
//		assertEquals (3, result);
//	}
//
//	@Test
//	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDrawAgainstATeamBelow () {
//		// Given
//		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
//		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");
//
//		when (mockFixture.getHomeTeam()).thenReturn(mockTeamForCalculation);
//		when (mockFixture.getHomeGoals()).thenReturn(3);
//		when (mockFixture.getAwayTeam()).thenReturn(mockOpposingTeam);
//		when (mockFixture.getAwayGoals()).thenReturn(3);
//		
//		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
//		when (mockPreviousTableRow.getParentTable()).thenReturn(mockParentTable);
//		
//		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRow);
//		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
//		
//		when (mockPreviousTableRow.getLeaguePosition()).thenReturn(3);
//		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(10);
//		
//		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRow, mockFixture);
//		
//		// When
//		int result = objectUnderTest.calculate();
//		
//		// Then
//		assertEquals (3, result);
//	}
//
//	@Test
//	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDefeatAgainstATeamAbove () {
//		// Given
//		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
//		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");
//
//		when (mockFixture.getHomeTeam()).thenReturn(mockTeamForCalculation);
//		when (mockFixture.getHomeGoals()).thenReturn(1);
//		when (mockFixture.getAwayTeam()).thenReturn(mockOpposingTeam);
//		when (mockFixture.getAwayGoals()).thenReturn(3);
//		
//		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
//		when (mockPreviousTableRow.getParentTable()).thenReturn(mockParentTable);
//		
//		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRow);
//		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
//		
//		when (mockPreviousTableRow.getLeaguePosition()).thenReturn(10);
//		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(3);
//		
//		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRow, mockFixture);
//		
//		// When
//		int result = objectUnderTest.calculate();
//		
//		// Then
//		assertEquals (3, result);
//	}
//
//	@Test
//	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDefeatAgainstATeamBelow () {
//		// Given
//		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
//		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");
//
//		when (mockFixture.getHomeTeam()).thenReturn(mockTeamForCalculation);
//		when (mockFixture.getHomeGoals()).thenReturn(1);
//		when (mockFixture.getAwayTeam()).thenReturn(mockOpposingTeam);
//		when (mockFixture.getAwayGoals()).thenReturn(3);
//		
//		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
//		when (mockPreviousTableRow.getParentTable()).thenReturn(mockParentTable);
//		
//		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRow);
//		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
//		
//		when (mockPreviousTableRow.getLeaguePosition()).thenReturn(3);
//		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(10);
//		
//		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRow, mockFixture);
//		
//		// When
//		int result = objectUnderTest.calculate();
//		
//		// Then
//		assertEquals (3, result);
//	}
//
//	@Test
//	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayWinAgainstATeamAbove () {
//		// Given
//		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
//		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");
//
//		when (mockFixture.getHomeTeam()).thenReturn(mockOpposingTeam);
//		when (mockFixture.getHomeGoals()).thenReturn(1);
//		when (mockFixture.getAwayTeam()).thenReturn(mockTeamForCalculation);
//		when (mockFixture.getAwayGoals()).thenReturn(3);
//		
//		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
//		when (mockPreviousTableRow.getParentTable()).thenReturn(mockParentTable);
//		
//		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRow);
//		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
//		
//		when (mockPreviousTableRow.getLeaguePosition()).thenReturn(10);
//		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(3);
//		
//		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRow, mockFixture);
//		
//		// When
//		int result = objectUnderTest.calculate();
//		
//		// Then
//		assertEquals (3, result);
//	}
//	
//	@Test
//	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayWinAgainstATeamBelow () {
//		// Given
//		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
//		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");
//
//		when (mockFixture.getHomeTeam()).thenReturn(mockOpposingTeam);
//		when (mockFixture.getHomeGoals()).thenReturn(1);
//		when (mockFixture.getAwayTeam()).thenReturn(mockTeamForCalculation);
//		when (mockFixture.getAwayGoals()).thenReturn(3);
//		
//		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
//		when (mockPreviousTableRow.getParentTable()).thenReturn(mockParentTable);
//		
//		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRow);
//		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
//		
//		when (mockPreviousTableRow.getLeaguePosition()).thenReturn(3);
//		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(10);
//		
//		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRow, mockFixture);
//		
//		// When
//		int result = objectUnderTest.calculate();
//		
//		// Then
//		assertEquals (3, result);
//	}
//	
//	@Test
//	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDrawAgainstATeamAbove () {
//		// Given
//		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
//		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");
//
//		when (mockFixture.getHomeTeam()).thenReturn(mockOpposingTeam);
//		when (mockFixture.getHomeGoals()).thenReturn(3);
//		when (mockFixture.getAwayTeam()).thenReturn(mockTeamForCalculation);
//		when (mockFixture.getAwayGoals()).thenReturn(3);
//		
//		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
//		when (mockPreviousTableRow.getParentTable()).thenReturn(mockParentTable);
//		
//		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRow);
//		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
//		
//		when (mockPreviousTableRow.getLeaguePosition()).thenReturn(10);
//		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(3);
//		
//		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRow, mockFixture);
//		
//		// When
//		int result = objectUnderTest.calculate();
//		
//		// Then
//		assertEquals (3, result);
//	}
//
//	@Test
//	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDrawAgainstATeamBelow () {
//		// Given
//		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
//		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");
//
//		when (mockFixture.getHomeTeam()).thenReturn(mockOpposingTeam);
//		when (mockFixture.getHomeGoals()).thenReturn(3);
//		when (mockFixture.getAwayTeam()).thenReturn(mockTeamForCalculation);
//		when (mockFixture.getAwayGoals()).thenReturn(3);
//		
//		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
//		when (mockPreviousTableRow.getParentTable()).thenReturn(mockParentTable);
//		
//		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRow);
//		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
//		
//		when (mockPreviousTableRow.getLeaguePosition()).thenReturn(3);
//		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(10);
//		
//		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRow, mockFixture);
//		
//		// When
//		int result = objectUnderTest.calculate();
//		
//		// Then
//		assertEquals (3, result);
//	}
//
//	@Test
//	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDefeatAgainstATeamAbove () {
//		// Given
//		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
//		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");
//
//		when (mockFixture.getHomeTeam()).thenReturn(mockOpposingTeam);
//		when (mockFixture.getHomeGoals()).thenReturn(3);
//		when (mockFixture.getAwayTeam()).thenReturn(mockTeamForCalculation);
//		when (mockFixture.getAwayGoals()).thenReturn(1);
//		
//		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
//		when (mockPreviousTableRow.getParentTable()).thenReturn(mockParentTable);
//		
//		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRow);
//		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
//		
//		when (mockPreviousTableRow.getLeaguePosition()).thenReturn(10);
//		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(3);
//		
//		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRow, mockFixture);
//		
//		// When
//		int result = objectUnderTest.calculate();
//		
//		// Then
//		assertEquals (3, result);
//	}
//
//	@Test
//	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDefeatAgainstATeamBelow () {
//		// Given
//		when (mockTeamForCalculation.getTeamId()).thenReturn("TEAMFORCALC");
//		when (mockOpposingTeam.getTeamId()).thenReturn("OPPOSINGTEAM");
//
//		when (mockFixture.getHomeTeam()).thenReturn(mockOpposingTeam);
//		when (mockFixture.getHomeGoals()).thenReturn(3);
//		when (mockFixture.getAwayTeam()).thenReturn(mockTeamForCalculation);
//		when (mockFixture.getAwayGoals()).thenReturn(1);
//		
//		when (mockPreviousTableRow.getAttribute(TableRow.GAMES_WON)).thenReturn(3);
//		when (mockPreviousTableRow.getParentTable()).thenReturn(mockParentTable);
//		
//		when (mockParentTable.getTableRowForTeam("TEAMFORCALC")).thenReturn(mockPreviousTableRow);
//		when (mockParentTable.getTableRowForTeam("OPPOSINGTEAM")).thenReturn(mockPreviousTableRowForOpposingTeam);
//		
//		when (mockPreviousTableRow.getLeaguePosition()).thenReturn(3);
//		when (mockPreviousTableRowForOpposingTeam.getLeaguePosition()).thenReturn(10);
//		
//		objectUnderTest = new GamesWonAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockPreviousTableRow, mockFixture);
//		
//		// When
//		int result = objectUnderTest.calculate();
//		
//		// Then
//		assertEquals (3, result);
//	}
}
