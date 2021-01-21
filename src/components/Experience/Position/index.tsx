import { render } from '@testing-library/react'
import React from 'react'
import './Position.css'
import { Item, Label } from 'semantic-ui-react'
import { PositionInfo } from '../../../data/experienceData'

const Position = (props: PositionInfo) => {
    const { image, company, title, date, description, labels } = props;
    return (
     <Item className='Item'>
        <Item.Image src={image}/>
        <Item.Content>
          <Item.Header as='a'>{company}</Item.Header>
          <Item.Meta>
            <span className='cinema'>{title}</span>
          </Item.Meta>
          <Item.Meta>
            <span className='cinema'>{date}</span>
          </Item.Meta>
          <Item.Description>{description}</Item.Description>
          <Item.Extra>
              {labels.map(label => (<Label color={'teal'}>{label}</Label>))}
          </Item.Extra>
        </Item.Content>
      </Item>
    )
}

export default Position