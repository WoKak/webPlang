/**
 * Created by Michał on 2017-11-01.
 */

class Hello extends React.Component {
    render() {
        return (
            <p>{this.props.msg}</p>
        );
    }
}

React.render(<Hello msg="Strona w budowie / Page under construction"/>, document.getElementById('root'));