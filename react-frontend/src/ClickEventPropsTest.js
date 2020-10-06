import React, { Component } from "react";
import PropTypes from 'prop-types'

class ClickPropsTest extends Component {
    static defaultProps = {
        name: 'Default Name'
    }
    static propTypes = {
        name: PropTypes.string,
        age: PropTypes.number.isRequired
    }
    constructor(props) {
        super(props);
        this.state = {
            number: 0
        }
    }
    render() {
        return(
            <div>
                <p>ClickPropsTest name: { this.props.name }</p>
                <p>ClickPropsTest age: { this.props.age }</p>
                <p>ClickPropsTest Number: { this.state.number }</p>
                <button onClick={() => {
                    this.setState( {
                        number: this.state.number + 1
                    })
                }}>Plus Button</button>
            </div>
        )
    }
}

export default ClickPropsTest