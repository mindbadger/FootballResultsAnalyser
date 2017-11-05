package mindbadger.football.table.calculation;

import static mindbadger.football.table.AttributeIds.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import mindbadger.football.table.Table;
import mindbadger.football.table.TableRow;
import mindbadger.football.table.TableRowAfterResult;
import mindbadger.football.table.TeamFixtureContext;
import mindbadger.football.table.calculation.GoalsScoredInDrawsAtHomeVsTeamsAboveCalculation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mindbadger.football.domain.Fixture;
import mindbadger.football.domain.Team;

public class GoalsConcededInDrawsAtHomeVsTeamsAboveCalculationTest {
	private GoalsScoredInDrawsAtHomeVsTeamsAboveCalculation objectUnderTest;
	
	@Mock
	private TableRowAfterResult mockPreviousTableRow;

	@Mock
	private Fixture mockFixture;
	
	@Mock
	private Team mockTeamForCalculation;

	@Mock
	private TeamFixtureContext mockFixtureTeamContext;
	
	@Mock
	private TeamFixtureContext mockOppositionTeamContext;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeWinAgainstATeamAbove () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(GOALS_SCORED_IN_DRAWS_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GoalsScoredInDrawsAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeWinAgainstATeamBelow () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(GOALS_SCORED_IN_DRAWS_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GoalsScoredInDrawsAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnAnIncrementWhenThereIsAPreviousRowForAHomeDrawAgainstATeamAbove () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(2);
		when (mockFixture.getAwayGoals()).thenReturn(2);
		when (mockPreviousTableRow.getAttribute(GOALS_SCORED_IN_DRAWS_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GoalsScoredInDrawsAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (5, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDrawAgainstATeamBelow () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(2);
		when (mockFixture.getAwayGoals()).thenReturn(2);
		when (mockPreviousTableRow.getAttribute(GOALS_SCORED_IN_DRAWS_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GoalsScoredInDrawsAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDefeatAgainstATeamAbove () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getAttribute(GOALS_SCORED_IN_DRAWS_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GoalsScoredInDrawsAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAHomeDefeatAgainstATeamBelow () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(true);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getAttribute(GOALS_SCORED_IN_DRAWS_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GoalsScoredInDrawsAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayWinAgainstATeamAbove () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getAttribute(GOALS_SCORED_IN_DRAWS_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GoalsScoredInDrawsAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}
	
	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayWinAgainstATeamBelow () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(1);
		when (mockFixture.getAwayGoals()).thenReturn(3);
		when (mockPreviousTableRow.getAttribute(GOALS_SCORED_IN_DRAWS_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GoalsScoredInDrawsAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}
	
	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDrawAgainstATeamAbove () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(2);
		when (mockFixture.getAwayGoals()).thenReturn(2);
		when (mockPreviousTableRow.getAttribute(GOALS_SCORED_IN_DRAWS_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GoalsScoredInDrawsAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDrawAgainstATeamBelow () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(2);
		when (mockFixture.getAwayGoals()).thenReturn(2);
		when (mockPreviousTableRow.getAttribute(GOALS_SCORED_IN_DRAWS_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GoalsScoredInDrawsAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDefeatAgainstATeamAbove () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(true);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(GOALS_SCORED_IN_DRAWS_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GoalsScoredInDrawsAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}

	@Test
	public void shouldReturnNoChangeWhenThereIsAPreviousRowForAnAwayDefeatAgainstATeamBelow () {
		// Given
		when (mockFixtureTeamContext.isAtHome()).thenReturn(false);
		when (mockFixtureTeamContext.isPlayingTeamAbove()).thenReturn(false);
		when (mockFixture.getHomeGoals()).thenReturn(3);
		when (mockFixture.getAwayGoals()).thenReturn(1);
		when (mockPreviousTableRow.getAttribute(GOALS_SCORED_IN_DRAWS_AT_HOME_VS_ABOVE)).thenReturn(3);
		
		objectUnderTest = new GoalsScoredInDrawsAtHomeVsTeamsAboveCalculation(mockTeamForCalculation, mockFixture, mockFixtureTeamContext, mockOppositionTeamContext, mockPreviousTableRow);
		
		// When
		int result = objectUnderTest.calculate();
		
		// Then
		assertEquals (3, result);
	}
}