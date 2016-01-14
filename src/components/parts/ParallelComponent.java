package components.parts;

public class ParallelComponent extends Component {

    ParallelComponentView componentView;

    ParallelComponent(ParallelComponentView componentView, boolean composite){
        super(composite);
        this.componentView = componentView;
    }
}
