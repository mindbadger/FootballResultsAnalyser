package uk.co.mindbadger.footballresults.table.calculation;

import uk.co.mindbadger.footballresults.season.TeamFixtureContext;
import uk.co.mindbadger.footballresults.table.TableRow;
import uk.co.mindbadger.footballresultsanalyser.domain.Fixture;
import uk.co.mindbadger.footballresultsanalyser.domain.Team;

public class GoalsScoredInDefeatsAtHomeVsTeamsAboveCalculation extends CalculationForFixture<String, String, String> {
	public GoalsScoredInDefeatsAtHomeVsTeamsAboveCalculation(Team<String> team, Fixture<String> fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext, TableRow<String,String,String> previousTableRow) {
		super(team, fixture, fixtureTeamContext, oppositionTeamContext, previousTableRow);
	}

	@Override
	public int calculate(boolean reCalculate) {

		int goalsFor = (fixtureTeamContext.isAtHome() ? fixture.getHomeGoals() : fixture.getAwayGoals());
		int goalsAgainst = (fixtureTeamContext.isAtHome() ? fixture.getAwayGoals() : fixture.getHomeGoals());
		
		boolean lost = goalsFor < goalsAgainst;
		
		return ((lost && fixtureTeamContext.isAtHome() && fixtureTeamContext.isPlayingTeamAbove()) ?
				previousTableRow.getAttribute(TableRow.GOALS_SCORED_IN_DEFEATS_AT_HOME_VS_ABOVE) + goalsFor : previousTableRow.getAttribute(TableRow.GOALS_SCORED_IN_DEFEATS_AT_HOME_VS_ABOVE));
	}

}