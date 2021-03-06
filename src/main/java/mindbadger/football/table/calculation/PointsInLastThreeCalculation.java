package mindbadger.football.table.calculation;

import mindbadger.football.table.TableRow;
import mindbadger.football.table.TeamFixtureContext;
import mindbadger.football.domain.Fixture;
import mindbadger.football.domain.Team;

public class PointsInLastThreeCalculation extends CalculationForFixture<String, String, String> {
	public PointsInLastThreeCalculation(Team team, Fixture fixture, TeamFixtureContext fixtureTeamContext, TeamFixtureContext oppositionTeamContext, TableRow previousTableRow) {
		super(team, fixture, fixtureTeamContext, oppositionTeamContext, previousTableRow);
	}

	@Override
	public int calculate(boolean reCalculate) {
		TableRow previousTableRow1 = this.previousTableRow;
		TableRow previousTableRow2 = this.previousTableRow.getPreviousTableRow();
		TableRow previousTableRow3 = this.previousTableRow.getPreviousTableRow();
		
		return pointsForTableRow(previousTableRow1) + pointsForTableRow(previousTableRow2) + pointsForTableRow(previousTableRow3);
	}

	private int pointsForTableRow (TableRow tableRow) {
		if (tableRow != null && tableRow.getFixtureTeamContext() != null) {
			return tableRow.getFixtureTeamContext().getPoints();
		} else {
			return 0;
		}
	}
	
}
