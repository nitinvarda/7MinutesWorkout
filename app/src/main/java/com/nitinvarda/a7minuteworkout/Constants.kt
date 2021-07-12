package com.nitinvarda.a7minuteworkout

class Constants {
    companion object{
        fun defaultExerciseList():ArrayList<ExerciceModel>{
            val exerciseList = ArrayList<ExerciceModel>()


            val jumpingJacks = ExerciceModel(
                1,
                "Jumping Jacks",
                R.drawable.ic_jumping_jacks,
                false,
                false
            )

            exerciseList.add(jumpingJacks)

            val wallSit = ExerciceModel(2, "Wall Sit", R.drawable.ic_wall_sit, false, false)
            exerciseList.add(wallSit)

            val pushUp = ExerciceModel(3, "Push Up", R.drawable.ic_push_up, false, false)
            exerciseList.add(pushUp)

            val abdominalCrunch =
                ExerciceModel(4, "Abdominal Crunch", R.drawable.ic_abdominal_crunch, false, false)
            exerciseList.add(abdominalCrunch)

            val stepUpOnChair =
                ExerciceModel(
                    5,
                    "Step-Up onto Chair",
                    R.drawable.ic_step_up_onto_chair,
                    false,
                    false
                )
            exerciseList.add(stepUpOnChair)

            val squat = ExerciceModel(6, "Squat", R.drawable.ic_squat, false, false)
            exerciseList.add(squat)

            val tricepDipOnChair =
                ExerciceModel(
                    7,
                    "Tricep Dip On Chair",
                    R.drawable.ic_triceps_dip_on_chair,
                    false,
                    false
                )
            exerciseList.add(tricepDipOnChair)

            val plank = ExerciceModel(8, "Plank", R.drawable.ic_plank, false, false)
            exerciseList.add(plank)

            val highKneesRunningInPlace =
                ExerciceModel(
                    9, "High Knees Running In Place",
                    R.drawable.ic_high_knees_running_in_place,
                    false,
                    false
                )
            exerciseList.add(highKneesRunningInPlace)

            val lunges = ExerciceModel(10, "Lunges", R.drawable.ic_lunge, false, false)
            exerciseList.add(lunges)

            val pushupAndRotation =
                ExerciceModel(
                    11,
                    "Push up and Rotation",
                    R.drawable.ic_push_up_and_rotation,
                    false,
                    false
                )
            exerciseList.add(pushupAndRotation)

            val sidePlank = ExerciceModel(12, "Side Plank", R.drawable.ic_side_plank, false, false)
            exerciseList.add(sidePlank)

            return exerciseList
        }
    }
}