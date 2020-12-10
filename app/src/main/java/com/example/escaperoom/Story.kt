package com.example.escaperoom

import android.view.View
import android.view.View.INVISIBLE
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.escaperoom.databinding.ActivityGameScreenBinding


class Story(val gamescr: ActivityGameScreenBinding) {

    var nextPosition1 = ""
    var nextPosition2 = ""
    var nextPosition3 = ""
    var nextPosition4 = ""
    var masterSword = false
    var killedPlant = false

    fun selectPosition(position: String){

        when(position){
            "startingPoint" -> startingPoint()
            "sign" -> sign()
            "pipe" -> pipe()
            "plant" -> plant()
            "sword" -> sword()
            "monster" -> monster()
            "attack" -> attack()
            "dead" -> dead()
            "goTitleScreen" -> goTitleScreen(gamescr)
            "goNextRoom" -> nextRoom(gamescr)

        }
    }

    fun goTitleScreen(gamescr: ActivityGameScreenBinding) {
       Navigation.findNavController(gamescr.choiceButton1).navigate(R.id.action_gameScreen_to_titleScreen)

    }

    fun nextRoom(gamescr: ActivityGameScreenBinding) {
        Navigation.findNavController(gamescr.choiceButton1).navigate(R.id.action_gameScreen_to_nextRoomFragment)

    }

    fun showAllButtons(){
        gamescr.choiceButton1.setVisibility(View.VISIBLE)
        gamescr.choiceButton2.setVisibility(View.VISIBLE)
        gamescr.choiceButton3.setVisibility(View.VISIBLE)
        gamescr.choiceButton4.setVisibility(View.VISIBLE)
    }

    fun startingPoint(){
        gamescr.gameImageView.setImageResource(R.drawable.run)
        gamescr.gameTextView.setText("You are on the road. There is a wooden sign nearby. \n\nWhat will you do?")

        gamescr.choiceButton1.setText("Go north")
        gamescr.choiceButton2.setText("Go east")
        gamescr.choiceButton3.setText("Go west")
        gamescr.choiceButton4.setText("Read the sign")

        showAllButtons()

        nextPosition1 = "monster"
        nextPosition2 = "sword"
        nextPosition3 = "pipe"
        nextPosition4 = "sign"
    }

    fun sign(){

        gamescr.gameImageView.setImageResource(R.drawable.sign)
        gamescr.gameTextView.setText("The sign says: \n\n MONSTER AHEAD!!!")

        gamescr.choiceButton1.setText("Back")

        gamescr.choiceButton2.setVisibility(View.INVISIBLE)
        gamescr.choiceButton3.setVisibility(View.INVISIBLE)
        gamescr.choiceButton4.setVisibility(View.INVISIBLE)

        nextPosition1 = "startingPoint"
    }


    fun pipe(){
        gamescr.gameImageView.setImageResource(R.drawable.pipe)
        gamescr.gameTextView.setText("You find a gigantic pipe.")

        gamescr.choiceButton1.setText("Look inside")
        gamescr.choiceButton2.setText("Go back")

        gamescr.choiceButton3.setVisibility(View.INVISIBLE)
        gamescr.choiceButton4.setVisibility(View.INVISIBLE)

        nextPosition1 = "plant"
        nextPosition2 = "startingPoint"
    }

    fun plant(){

        if(masterSword == false){
            gamescr.gameImageView.setImageResource(R.drawable.piranhaplant)
            gamescr.gameTextView.setText("Piranha plant is inside!!!\n\nAlas, you are eaten by it.")

            gamescr.choiceButton1.setText("...")

            gamescr.choiceButton2.setVisibility(View.INVISIBLE)
            gamescr.choiceButton3.setVisibility(View.INVISIBLE)
            gamescr.choiceButton4.setVisibility(View.INVISIBLE)

            nextPosition1 = "dead"
        }

        if (masterSword == true){

            gamescr.gameImageView.setImageResource(R.drawable.swordman)
            gamescr.gameTextView.setText("You have defeated the Pirahna plant with your master sword!!!\n You feel much stronger now!")

            killedPlant = true

            gamescr.choiceButton1.setText("Back to the road")

            gamescr.choiceButton2.setVisibility(View.INVISIBLE)
            gamescr.choiceButton3.setVisibility(View.INVISIBLE)
            gamescr.choiceButton4.setVisibility(View.INVISIBLE)

            nextPosition1 = "startingPoint"
        }
    }

    fun sword(){
        gamescr.gameImageView.setImageResource(R.drawable.sword)
        gamescr.gameTextView.setText("Amazing! You find a master sword!! \n\n You have a master sword now.")

        masterSword = true

        gamescr.choiceButton1.setText("Back")

        gamescr.choiceButton2.visibility = INVISIBLE
        gamescr.choiceButton3.setVisibility(View.INVISIBLE)
        gamescr.choiceButton4.setVisibility(View.INVISIBLE)

        nextPosition1 = "startingPoint"
    }

    fun monster(){
        gamescr.gameImageView.setImageResource(R.drawable.monster1)
        gamescr.gameTextView.text = "You encounter a stranger thing..."

        masterSword = true

        gamescr.choiceButton1.text = "Attack!"
        gamescr.choiceButton2.text = "Run!"

        gamescr.choiceButton3.visibility = INVISIBLE
        gamescr.choiceButton4.visibility = INVISIBLE

        nextPosition1 = "attack"
        nextPosition2 = "startingPoint"
    }

    fun attack(){

        if(masterSword && killedPlant){
            gamescr.gameImageView.setImageResource(R.drawable.win)
            gamescr.gameTextView.textSize = 22F
            gamescr.gameTextView.text = "With your legendary Master Sward and experience as a warrior, the monster has no chance to win! \n\n You defeat the Monster and find the key!!"

            gamescr.choiceButton1.text = "Next Room"

            gamescr.choiceButton2.visibility = INVISIBLE
            gamescr.choiceButton3.visibility = INVISIBLE
            gamescr.choiceButton4.visibility = INVISIBLE

            nextPosition1 = "goNextRoom"
        }
        else{
            gamescr.gameImageView.setImageResource(R.drawable.halfbody)
            gamescr.gameTextView.text = "You fight well but the monster is too strong for you!"

            gamescr.choiceButton1.text = "..."

            gamescr.choiceButton2.visibility = INVISIBLE
            gamescr.choiceButton3.visibility = INVISIBLE
            gamescr.choiceButton4.visibility = INVISIBLE

            nextPosition1 = "dead"
        }
    }


    fun dead(){
        gamescr.gameImageView.setImageResource(R.drawable.grave)
        gamescr.gameTextView.setText("You are dead. Your escape attempt has failed.")

        gamescr.choiceButton1.setText("Try Again")

        gamescr.choiceButton2.visibility = INVISIBLE
        gamescr.choiceButton3.visibility = INVISIBLE
        gamescr.choiceButton4.visibility = INVISIBLE

        nextPosition1 = "goTitleScreen"
    }
}

