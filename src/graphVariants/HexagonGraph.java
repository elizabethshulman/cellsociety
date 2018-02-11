package graphVariants;

import cellsociety_team10.FileProcessor;
import rulesVariants.RulesFactory;
import visualComponents.Container;

public class HexagonGraph extends Graph {
	public HexagonGraph(FileProcessor file_processor, RulesFactory rules_factory) {
		super(file_processor, rules_factory);
	}

	@Override
	public void adjustRows(int new_rows) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adjustCols(int new_cols) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Container createContainer() {
		return myContainerFactory.create("Hexagon");
	}
}
