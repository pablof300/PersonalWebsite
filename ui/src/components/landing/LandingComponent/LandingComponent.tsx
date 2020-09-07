import React from "react"
import { AboutComponent } from "../about/AboutComponent/index"
import { ProjectsComponent } from "../projects/ProjectsComponent/index"
import { DownIconComponent } from "../../utility/DownIconComponent/index"
import { SkillsComponent } from "../skills/SkillsComponent/index"
import { ResumeComponent } from "../resume/ResumeComponent/index"
import { ContactComponent } from "../contact/ContactComponent/index"
import { PersonalWebsiteApi, FullWebsiteInfo } from '../../../api/index'
import {ExperiencesComponent} from "../experience/ExperiencesComponent";

interface State {
  websiteInfo?: FullWebsiteInfo
}

export class LandingComponent extends React.Component<{}, State> {

  constructor(props: {}) {
    super(props)
    this.state = { websiteInfo: undefined }
  }

  render() {
    const firstParagraph = 'I am rising junior at the University of Florida, go Gators! ' +
        'I am studying computer science and statistics, and trying to learn other new things along the way.';
    const secondParagraph = 'I spend most of my time learning new languages and tools,' +
        ' with an interest in distributed systems, full-stack development, and finance.'  +
        ' If I am not studying at Marston Library or hacking away, I am going for a run or playing ping pong.';

    const projects =  [
        {
            name: 'Singularities',
            type: 'Team Project',
            description: 'Lead developer of Singularities, an iOS game for iPhone and iPad. Developed using Swift and SpriteKit as a two-dimensional retro gravity game. It has been downloaded more than 500 times on the AppStore with a review of 4.8 stars.',
            funFact: 'This is my favorite project, and also one of the most stressful... (incredibly challenging, but equally satisfying)',
            url: 'https://apps.apple.com/us/app/singularities/id1278565563',
            firstImagePath: 'singularities-1.png',
            secondImagePath: 'singularities-2.jpg',
            year: 2018,
            priority: 2,
            id: '2',
        },
        {
            name: 'EarthSkipper',
            type: 'Hackathon Project',
            description: 'Written, tested, and deployed in under 24 hours. EarthSkipper is a web-application to promote spontaneous travel around the world. Developed using Ruby on Rails, FourSquare API, and Amazon S3. Awarded \'Best First Hack\' at the University of Miami\'s hackathon UHack 2018',
            funFact: 'You can find a demo of this project at pabloestrada.me/earthskipper',
            url: 'https://github.com/pablof300/EarthSkipper',
            firstImagePath: 'earthskipper-1.png',
            secondImagePath: 'earthskipper-2.png',
            year: 2018,
            priority: 3,
            id: '3',
        },
        {
            name: 'Orangutan Animatronics',
            type: 'Team Project',
            description: 'Interactive RFID-capable hydraulic powered orangutan that raises awareness about his specie/environment through speech. I was the lead engineer for wiring and programming, using a Raspberry Pi and Python. Second place winner at the 2018 Technology Student Association Florida State conference.',
            funFact: 'This project made me realize I love tinkering with electronics and the programming behind physical actuators!',
            url: 'https://github.com/pablof300/Orangutan-Animatronic',
            firstImagePath: 'animatronics-1.png',
            secondImagePath: 'animatronics-2.png',
            year: 2018,
            priority: 4,
            id: '4',
        },
        {
            name: 'Cubical World',
            type: 'Team Project',
            description: 'Developer of the iOS game Cubical World. It is a multi-level game written in Swift, using the SpriteKit framework. It is a simple, yet entertaining, game that took several months to finish due to its vast range of levels.',
            funFact: 'This was my first iOS game that I developed. Though it isn\'t perfect, it taught me so much about taking on and managing bigger projects.',
            url: 'https://apps.apple.com/us/app/cubical-world/id1273263048',
            firstImagePath: 'cubical-1.png',
            secondImagePath: 'cubical-2.png',
            year: 2017,
            priority: 5,
            id: '5',
        },
        {
            name: 'Mancala',
            type: 'Personal Project',
            description: 'Ancient family game rewritten in Java, using JavaFX for smooth animations and graphics. It supports singleplayer and multiplayer, and it is an open-sourced project.',
            funFact: 'After testing this game thousands of times, I\'ve become really good at Mancala',
            url: 'https://github.com/pablof300/Mancala',
            firstImagePath: 'mancala-1.png',
            secondImagePath: 'mancala-2.png',
            year: 2017,
            priority: 6,
            id: '6',
        }
        ]



    return (
      <>
        <AboutComponent paragraphs={{first: firstParagraph, second: secondParagraph}} />
        <DownIconComponent />
        <ExperiencesComponent />
        <DownIconComponent />
        <ProjectsComponent projects={projects}/>
        <DownIconComponent />
        <SkillsComponent techLanguages={'Java, TypeScript, Python, C++, Swift, Ruby, SQL & BigQuery'} frameworks={'React, Kafka, Rails, JavaFX, SpriteKit'} tools={'Docker, CI/CD, Git, Photoshop, Mode'} languages={'English, Spanish (Fluent)'} />
        <DownIconComponent />
        <ResumeComponent resumeLink={'https://www.dropbox.com/s/01it19mwxg9fq4o/EstradaPablo.pdf?dl=0s'}/>
        <DownIconComponent />
        <ContactComponent />
      </>
    )
  }
}
